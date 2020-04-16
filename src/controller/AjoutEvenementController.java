/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import IServices.ServiceEvenement;
import entites.Evenement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import services.ServiceEvenementImpl;
import utlis.MaConnection;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField type;
    @FXML
    private TextArea desc;
    @FXML
    private Button image;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private TextField nbplace;
    @FXML
    private ToggleButton active;
    @FXML
    private TableColumn<Evenement, String> tit;
    @FXML
    private TableColumn<Evenement, String> im;
    @FXML
    private TableColumn<Evenement, Date> dd;
    @FXML
    private TableColumn<Evenement, Date> df;
    @FXML
    private TableColumn<Evenement, String> ty;
    @FXML
    private TableColumn<Evenement, String> des;
    @FXML
    private TableColumn<Evenement, Boolean> act;
    @FXML
    private TableColumn<Evenement, Integer> nbp;
    @FXML
    private TableView<Evenement> evenementsTableView;
    @FXML
    private TableColumn<Evenement, Integer> idt;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddOrUpdateEvenement;
    @FXML
    private TextField searchField;
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnUpdate;

    Connection connection = MaConnection
            .getInstance()
            .getConnection();

    private final ServiceEvenement serviceEvenement = new ServiceEvenementImpl();
    private Evenement selectedEvenement;

    List<Evenement> evenements = serviceEvenement.findAll();
    private final static String ADD_TEXT = "ajouter";
    private final static String EDIT_TEXT = "mettre à jour";
    private Button detailEvent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.btnAddOrUpdateEvenement.setText(ADD_TEXT);
        initTableCols();
        this.displayTableEvenement();

        // Delete Evenement Listener
        btnDelete.setOnAction((ActionEvent e) -> {
            Evenement evenement = (Evenement) evenementsTableView.getSelectionModel().getSelectedItem();
            deleteEvenement(evenement);
        });

        // Search Field Listener
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.filterTableData(newValue);
           
        });
    }

    private void deleteEvenement(Evenement evenement) {
        if (evenement != null) {
            int id = evenement.getId();
            serviceEvenement.delete(id);
            this.displayTableEvenement();
            this.displayAlert("CONFIRMATION Suppression", "DELETE DONE", "", Alert.AlertType.INFORMATION);
        } else {
            this.displayAlert("CONFIRMATION Suppression", "Suppression echouée ", "", Alert.AlertType.ERROR);
        }
    }

    private void displayTableEvenement() {
        evenements = serviceEvenement.findAll();
        ObservableList<Evenement> obsList = FXCollections.observableArrayList(evenements);
        evenementsTableView.setItems(obsList);

    }

    public void filterTableData(String filterText) {
        ObservableList<Evenement> obList = FXCollections.observableArrayList(evenements);
        FilteredList<Evenement> filteredData = new FilteredList<>(obList);

        filteredData.setPredicate(evenement -> {
            if (filterText == null || filterText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = filterText.toLowerCase();
            if (evenement.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(evenementsTableView.comparatorProperty());
        evenementsTableView.setItems(sortedData);
    }

    @FXML
    private void addOrUpdateEvenement(ActionEvent event) throws SQLException {
             java.sql.Date date1 = java.sql.Date.valueOf(dated.getValue());
            java.sql.Date date2 = java.sql.Date.valueOf(datef.getValue());
        if (btnAddOrUpdateEvenement.getText().equals(ADD_TEXT)) {
            // Mode ajout
            if (titre.getText().length() == 0 || type.getText().length() == 0 || desc.getText().length() == 0 || nbplace.getText().length() == 0 || date2.before(date1) ) {
                displayAlert("veuillez remplir!!", "WARNING !", "some field are empty !!", Alert.AlertType.WARNING);
       
           
             return;
            } else {
                Evenement evenenement = createEvenementFromFields();
                try {
                    serviceEvenement.AjoutEvent(evenenement);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else // Mode Edit    
        {
            if (titre.getText().length() == 0 || type.getText().length() == 0 || desc.getText().length() == 0 || nbplace.getText().length() == 0) {
                displayAlert("veuillez remplir!!", "WARNING !", "some field are empty !!", Alert.AlertType.WARNING);
                 
             return;
            } else {
                Evenement evenenement = createEvenementFromFields();
                evenenement.setId(selectedEvenement.getId());
                try {
                    serviceEvenement.updateEvent(evenenement);
                    this.doClearFields();
                    this.btnAddOrUpdateEvenement.setText(ADD_TEXT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.displayTableEvenement();
    }

    public void displayAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public Evenement createEvenementFromFields() {
        return new Evenement(
                titre.getText(),
                image.getText(),
                Date.valueOf(dated.getValue()),
                Date.valueOf(datef.getValue()),
                type.getText(),
                desc.getText(),
                active.isSelected(),
                Integer.parseInt(nbplace.getText())
        );
    }

    @FXML
    private void attacher_photo(ActionEvent event) {

        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www\\jardin\\web\\uploads\\events_photos\\";
        image.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void clearFields(ActionEvent event) {
        doClearFields();
    }

    public void doClearFields() {
        titre.clear();
        type.clear();
        desc.clear();
        nbplace.clear();
    }

    private void initTableCols() {
        idt.setCellValueFactory(new PropertyValueFactory<>("id"));
        tit.setCellValueFactory(new PropertyValueFactory<>("titre"));
        im.setCellValueFactory(new PropertyValueFactory<>("image"));
        dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        ty.setCellValueFactory(new PropertyValueFactory<>("type"));
        des.setCellValueFactory(new PropertyValueFactory<>("description"));
        act.setCellValueFactory(new PropertyValueFactory<>("active"));
        nbp.setCellValueFactory(new PropertyValueFactory<>("nombreDePlace"));

    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        doClearFields();
        this.selectedEvenement = (Evenement) evenementsTableView.getSelectionModel().getSelectedItem();
        this.fillFields(selectedEvenement);
        btnAddOrUpdateEvenement.setText(EDIT_TEXT);
    }

    public void fillFields(Evenement evenement) {
        this.titre.setText(evenement.getTitre());
        this.dated.setValue(evenement.getDate_debut().toLocalDate());
        this.datef.setValue(evenement.getDate_fin().toLocalDate());
        this.type.setText(evenement.getType());
        this.desc.setText(evenement.getDescription());
        this.act.setEditable(evenement.isActive());
        this.nbplace.setText(evenement.getNombreDePlace() + "");
        this.active.setSelected(evenement.isActive());
    }

    @FXML
    private void exportExcel(ActionEvent event) throws FileNotFoundException, IOException, SQLException {

        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < this.evenementsTableView.getColumns().size(); j++) {
            row.createCell(j).setCellValue(evenementsTableView.getColumns().get(j).getText());
        }
        for (int i = 0; i < evenementsTableView.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < evenementsTableView.getColumns().size(); j++) {
                if (evenementsTableView.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(evenementsTableView.getColumns().get(j).getCellData(i).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        // Show save dialog
        Stage primary = new Stage();
        //FileChooser fileChooser = new FileChooser();
        // fileChooser.setTitle("Choisir un emplacement pour enregistrer le fichier excel");
        //        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//     File folder = fileChooser.showOpenDialog(primary);
        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setTitle("Choisir un emplacement pour enregistrer le fichier excel");
        File folder = dirChooser.showDialog(primary);
        if (folder != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(folder.getAbsolutePath()).append("\\evenement.xls");
            FileOutputStream fileOut = new FileOutputStream(builder.toString());
            workbook.write(fileOut);
            fileOut.close();
        }

    }
//    private void Excel(File file)
//            throws FileNotFoundException, IOException, SQLException {
//
//        FileOutputStream fileOut = new FileOutputStream(file);

//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet workSheet = workbook.createSheet("evenements");
//
//        HSSFFont fontBold = workbook.createFont();
//        fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        HSSFCellStyle styleBold = workbook.createCellStyle();
//        styleBold.setFont(fontBold);         
    // -------------------- excel
//        XSSFRow row1 = workSheet.createRow((short) 0);
//            
//            workSheet.autoSizeColumn(8);
//            row1.createCell(0).setCellValue("titre");
//            row1.createCell(1).setCellValue("image");
//            row1.createCell(2).setCellValue("date_debut");
//             row1.createCell(3).setCellValue("date_fin");
//              row1.createCell(4).setCellValue("type");
//               row1.createCell(5).setCellValue("description");
//                row1.createCell(6).setCellValue("active");
//                 row1.createCell(7).setCellValue("nombreDePlace");
//            Row row2;
//
//            String req = "SELECT * from evenement ";
//            
//            PreparedStatement ps = connection.prepareStatement(req);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                int a = rs.getRow();
//                row2 = workSheet.createRow((short) a); 
//               
//               row2.createCell(0).setCellValue(rs.getString(2));
//               row2.createCell(1).setCellValue(rs.getString(3));
//               row2.createCell(2).setCellValue(rs.getString(4));
//               row2.createCell(3).setCellValue(rs.getString(5));
//               row2.createCell(4).setCellValue(rs.getString(6));
//               row2.createCell(5).setCellValue(rs.getString(7));
//               row2.createCell(6).setCellValue(rs.getString(8));
//                row2.createCell(7).setCellValue(rs.getString(9));
//               System.out.println(rs.getString(4)+""+ rs.getString(9) +""+a);
//             //  row2.createCell(2).setCellValue(rs.getString(3));         
//            }         
//            try{
//            workbook.write(fileOut);
//            fileOut.flush();
//            fileOut.close();
//            rs.close();
//        
//        } catch (SQLException e) {
//            e.getStackTrace();
//            System.out.println("controller.AjoutEveController.ExcelAction()"); 
//        }
//    }
    /* @FXML
    private void detailEvent(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/detailsEvenement.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    */
    

    
}
