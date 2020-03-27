/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import empdata.implement.implEmpdata;
import empdata.model.modelEmpdata;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import Iservice.Iabonnement;

/**
 * FXML Controller class
 *
 * @author kjpsaycon
 */

//controller empdata
public class AbonnementController implements Initializable {
    @FXML
    private Tab tabBirthChart,tabBiodata;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtAddress;
    @FXML
    private DatePicker cmbDate;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<modelEmpdata> tableData;
    @FXML
    private TableColumn<modelEmpdata, String> colId;
    @FXML
    private TableColumn<modelEmpdata, String> colName;
    @FXML
    private TableColumn<modelEmpdata, String> colAddress;
    @FXML
    private TableColumn<modelEmpdata, String> colDate;
    @FXML
    private TableColumn colAction;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private AnchorPane paneLoadGraph;
    interEmpdata crudData = new implEmpdata();
    ObservableList<modelEmpdata> listData;
    private String statusCode,statusClick;
    ObservableList<modelEmpdata> listDelete;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory((TableColumn.CellDataFeatures<modelEmpdata, String> cellData) ->
                        cellData.getValue().idProperty());
        colName.setCellValueFactory((TableColumn.CellDataFeatures<modelEmpdata, String> cellData) ->
                        cellData.getValue().nameProperty());
        colAddress.setCellValueFactory((TableColumn.CellDataFeatures<modelEmpdata, String> cellData) ->
                        cellData.getValue().addressProperty());
        colDate.setCellValueFactory(new PropertyValueFactory("dateFormat"));
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        colAction.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(tableData);
            }
        });
        listData = FXCollections.observableArrayList();
        AwesomeDude.setIcon(btnSave, AwesomeIcon.CHECK_SQUARE, "15px");
        AwesomeDude.setIcon(btnRefresh, AwesomeIcon.CHAIN_BROKEN, "15px");
        cmbDate.setValue(LocalDate.of(1990, 01, 01));
        statusCode = "0";
        statusClick = "0";
        showData();
        autoId();
        tableData.getSelectionModel().clearSelection();
            
    }
   
  
   
    private void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
   
    private void clear(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSearch.clear();
        cmbDate.setValue(LocalDate.of(1990, 01, 01));
        statusCode = "0";
    }
   
    private void showData(){
        listData = crudData.getAll();
        tableData.setItems(listData);
    }
   
    private void autoId(){
        modelEmpdata m = new modelEmpdata();
        crudData.autoId(m);
        txtId.setText(m.getId());
    }

    @FXML
    private void actionSave(ActionEvent event) {
        modelEmpdata m = new modelEmpdata();
        m.setId(txtId.getText());
        m.setName(txtName.getText());
        m.setAddress(txtAddress.getText());
        m.setBirthDate(Date.valueOf(cmbDate.getValue()));
        if (statusCode.equals("0")) {
            crudData.insert(m);
        }else{
            crudData.update(m);
        }
        dialog(Alert.AlertType.INFORMATION, "Data has saved");
        showData();
        clear();
        autoId();
       
    }

    @FXML
    private void tableDataClick(MouseEvent event) {
        if (statusClick.equals("1")) {
            statusCode = "1";
            try {
                modelEmpdata click = tableData.getSelectionModel().getSelectedItems().get(0);
                txtId.setText(click.getId());
                txtName.setText(click.getName());
                txtAddress.setText(click.getAddress());
                cmbDate.setValue(LocalDate.parse(click.getBirthDate().toString()));
            } catch (Exception e) {
            }
        }
       
    }

    @FXML
    private void actionSearch(KeyEvent event) {
        listData = crudData.likeByName(txtSearch.getText());
        tableData.setItems(listData);
    }

    @FXML
    private void actionRefresh(ActionEvent event) {
        clear();
        showData();
        autoId();
    }
   
    private class ButtonCell extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonDelete = new Hyperlink("Delete");
        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final HBox hb = new HBox(cellButtonDelete,cellButtonEdit);
        ButtonCell(final TableView tblView){
            hb.setSpacing(4);
           
            //cell delete
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                statusClick = "1";
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                tableDataClick(null);
                modelEmpdata m = new modelEmpdata();
                m.setId(txtId.getText());
                crudData.delete(m);
                showData();
                clear();
                autoId();
                dialog(Alert.AlertType.INFORMATION, "Data has successfully removed");
                statusClick = "0";
                statusCode = "0";
            });
           
            //cell edit
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                statusClick = "1";
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                tableDataClick(null);
                statusClick = "0";
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
    }
   
}
