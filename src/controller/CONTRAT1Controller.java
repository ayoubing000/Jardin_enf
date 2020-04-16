/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.contrat;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.serviceContrat;

/**
 * FXML Controller class
 *
 * @author souhaib
 */
public class CONTRAT1Controller implements Initializable {

                    serviceContrat s = new  serviceContrat() ;
    contrat p = new contrat();

    private Text aff;
    @FXML
    private TextField I1;
    @FXML
    private TextField I2;
    private TextField I7;
    @FXML
    private DatePicker I3;
    @FXML
    private DatePicker I4;
    @FXML
    private DatePicker I5;
    @FXML
    private DatePicker I6;
            private ObservableList<contrat> data;

    @FXML
    private TableView<contrat> list;
    @FXML
    private TableColumn<contrat, Integer> id;
    @FXML
    private TableColumn<contrat, String> type;
    @FXML
    private TableColumn<contrat, Date> date_signature;
    @FXML
    private TableColumn<contrat, Date> date_debut;
    @FXML
    private TableColumn<contrat, Date> date_fin;
    @FXML
    private TableColumn<contrat, Date> date_resiliation;
    @FXML
    private TableColumn<contrat, Integer> enseigniant;
    @FXML
    private ComboBox<String> ensid;
    @FXML
    private TextField idrecherche;
    @FXML
    private Text LIBELLEA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                          data = FXCollections.observableArrayList();
                         loadDataFromDatabase();
       setsCllTable();
       fillcombox1();
       setcellValue();
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException{
        
               if (I1.getText().length() == 0 || I2.getText().length() == 0 ||  I3.getValue()==null||I4.getValue()==null||I5.getValue()==null||I6.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();  
       }    
               java.sql.Date date1 = java.sql.Date.valueOf(I3.getValue());
        java.sql.Date date2 = java.sql.Date.valueOf(I4.getValue());
     
        java.sql.Date date3 = java.sql.Date.valueOf(I5.getValue());
        java.sql.Date date4= java.sql.Date.valueOf(I6.getValue());
        if (date1.after(date2)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("date signature avant la date de debut!!");
            alert.showAndWait();  
       }   
                else if (date2.after(date3)){      Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("il faut que la date de debut est inferieur a la date fin!!");
            alert.showAndWait();  
       }
                else  if (date4.after(date3)||date4.before(date2)){      
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("la date de resiliation est entre la date de debut et la date !!");
            alert.showAndWait();  
       }
               else {
                 serviceContrat s = new  serviceContrat() ;
    contrat p = new contrat();
    int i=0 ;
    int j=0;
     i = Integer.parseInt(I1.getText());
     p.setId(i);
     p.setType(I2.getText());
  
           p.setDate_signature(date1);
         
      p.setDate_debut(date2);
      p.setDate_fin(date3);
      p.setDate_resiliation(date4);
   
    String output =  ensid.getSelectionModel().getSelectedItem().toString();
             Integer J = Integer.parseInt(output);

     p .setEnseignant_id(J);
     
    
    s.ajouterContrat(p);
    data.clear();
                loadDataFromDatabase();

    }   
             
            
    }

    private void Afficher(ActionEvent event) throws SQLException {
       serviceContrat s = new  serviceContrat() ;
           aff.setText(s.afficherContrat().toString());
    }
        private void setsCllTable() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date_signature.setCellValueFactory(new PropertyValueFactory<>("date_signature"));
         date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        date_resiliation.setCellValueFactory(new PropertyValueFactory<>("date_resiliation"));
       enseigniant.setCellValueFactory(new PropertyValueFactory<>("enseignant_id"));
        
    }
 
  private void loadDataFromDatabase() {
       List<contrat> lisabn =s.afficherContrat();
        for(contrat e:lisabn){
            System.out.println(e);
            data.add(e);
        }
           list.setItems(data);
    } 

     @FXML
    private void supprimer(ActionEvent event)throws SQLException, IOException {
          try {
               s.deleteContrat(p);
               data.clear();
               loadDataFromDatabase();
           } catch (SQLException ex) {
               Logger.getLogger(CONTRAT1Controller.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
    

    @FXML
    private boolean modifier(ActionEvent event)throws SQLException, IOException {
           p.setType(type.getText());
      
      String output =   ensid.getSelectionModel().getSelectedItem().toString();
      Integer I = Integer.parseInt(output);
  
             p.setEnseignant_id(I);
             
           
  
        int i=0 ;
    
     i = Integer.parseInt(I1.getText());
                  s.updateContrat(i,I2.getText());
        data.clear();
        loadDataFromDatabase();
             
            return true;
    }

    @FXML
    private void imprimer(ActionEvent event) throws   ClassNotFoundException, SQLException, DocumentException, BadElementException, IOException, URISyntaxException {
   
         try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardin", "root", "");
      PreparedStatement pt = con.prepareStatement("select * from contrat");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                        my_pdf_report.open();  
                       my_pdf_report.add(new Paragraph(new Date().toString()));
                            Image img = Image.getInstance("c:/6.png");
                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("Listes des contrats"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(7);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" conrat id"));
                                      table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("type"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date signature"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date debut"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date fin"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date resiliation"));
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("enseignant id"));
                                       
                                       table_cell.setBackgroundColor(BaseColor.PINK);
                                       my_report_table.addCell(table_cell);

                                      while(rs.next()){
                                      
                                       String contrat_id = rs.getString("id");
                                       table_cell=new PdfPCell(new Phrase(contrat_id));
                                       my_report_table.addCell(table_cell);
                                       String type=rs.getString("type");
                                       table_cell=new PdfPCell(new Phrase(type));
                                       my_report_table.addCell(table_cell);
                                       String ds=rs.getString("date_signature");
                                       table_cell=new PdfPCell(new Phrase(ds));
                                       my_report_table.addCell(table_cell);
                                       String dd=rs.getString("date_debut");
                                       table_cell=new PdfPCell(new Phrase(dd));
                                       my_report_table.addCell(table_cell);
                                        String df = rs.getString("date_fin");
                                       table_cell=new PdfPCell(new Phrase(df));
                                       my_report_table.addCell(table_cell);
                                        String dr = rs.getString("date_resiliation");
                                       table_cell=new PdfPCell(new Phrase(dr));
                                       my_report_table.addCell(table_cell); 
                                       String enseignant_id = rs.getString("enseignant_id");
                                       table_cell=new PdfPCell(new Phrase(enseignant_id));
                                       my_report_table.addCell(table_cell);
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "imprimer avec succes");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
    }
      public void fillcombox1() 
    {
    try {
        List<String> idens =s.afficherIdEnesignant();
        ensid.setItems(FXCollections.observableArrayList(idens));
    } catch (SQLException ex) {
        Logger.getLogger(EMPLOI1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
        } 

        @FXML
    private void Chercher(ActionEvent event) throws SQLException {
              serviceContrat s = new  serviceContrat();
          int i=0 ;
     i = Integer.parseInt(idrecherche.getText());
     
          s.getcontratById(i);
                  LIBELLEA.setText(s.getcontratById(i).toString());
                  

    }
    private void setcellValue() {
    
         list.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             p = list.getItems().get(list.getSelectionModel().getSelectedIndex());
        
        String en_id = Integer.toString(p.getEnseignant_id());


     String ci = Integer.toString(p.getId());
        I1.setText(ci);
        
        I2.setText(p.getType());
        
        
       
                
         
         });
    }
}