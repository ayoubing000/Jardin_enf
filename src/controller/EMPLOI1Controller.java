/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.emploi;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.serviceEmploi;

/**
 * FXML Controller class
 *
 * @author souhaib
 */
public class EMPLOI1Controller implements Initializable {

serviceEmploi s= new  serviceEmploi() ;
      emploi E1 = new emploi();
    private Text textaffiche;
    @FXML
    private TextField I1;
    @FXML
    private TextField I3;
    private TextField I4;
    private TextField I5;
    @FXML
    private DatePicker I2;
        private ObservableList<emploi> data;

    @FXML
    private TableView<emploi> list;
    @FXML
    private TableColumn<emploi, Integer> id;
    @FXML
    private TableColumn<emploi, Date> jour;
    @FXML
    private TableColumn<emploi, String> desc;
    @FXML
    private TableColumn<emploi, Integer> ensei;
    @FXML
    private TableColumn<emploi, Integer> salle;
    @FXML
    private ComboBox<String> salleid;
@FXML
    private ComboBox<String> enseignantid;
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
       fillcombox();
       fillcombox1();
       setcellValue();
    }    

    
      
        
    
    @FXML
    private void AJOUTER(ActionEvent event) throws SQLException {

         
                if (I1.getText().length() == 0 ||I2.getValue()==null|| I3.getText().length() == 0||jour.getText().length()==0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();  
       }
                else{
  serviceEmploi s= new  serviceEmploi() ;
      emploi E1 = new emploi();
            int i=0 ;
            int j=0;
            int h =0 ;
            
     i = Integer.parseInt(I1.getText());
     E1.setId(i);
     java.sql.Date date1 = java.sql.Date.valueOf(I2.getValue());
     E1.setJour(date1);
     E1.setDescription(I3.getText());
    
      String output =  enseignantid.getSelectionModel().getSelectedItem().toString();
             Integer J = Integer.parseInt(output);
             E1.setEnseignant_id(J);
               String outputt =  salleid.getSelectionModel().getSelectedItem().toString();
             Integer H= Integer.parseInt(outputt);
             E1.setSalle_id(H);
  
    s.ajouterEmploi(E1);
    data.clear();
            loadDataFromDatabase();

                }
    }

    private void Afficher(ActionEvent event) throws SQLException {
serviceEmploi s= new  serviceEmploi() ;
          textaffiche.setText(s.afficherEmploi().toString());
    }
       private void setsCllTable() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        jour.setCellValueFactory(new PropertyValueFactory<>("jour"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
         ensei.setCellValueFactory(new PropertyValueFactory<>("enseignant_id"));
       salle.setCellValueFactory(new PropertyValueFactory<>("salle_id"));
        
    }
 
  private void loadDataFromDatabase() {
       List<emploi> lisabn =s.afficherEmploi();
        for(emploi e:lisabn){
            System.out.println(e);
            data.add(e);
        }
           list.setItems(data);
    } 

      @FXML
    private void supprimer(ActionEvent event)throws SQLException, IOException {
      try {
               s.deleteEmploi(E1);
             
               data.clear();
               loadDataFromDatabase();
           } catch (SQLException ex) {
               Logger.getLogger(EMPLOI1Controller.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    

    @FXML
    private boolean modifier(ActionEvent event)throws SQLException, IOException {
         
        E1.setDescription(desc.getText());
      
      String output =   salleid.getSelectionModel().getSelectedItem().toString();
      Integer I = Integer.parseInt(output);
  String outputT =  enseignantid.getSelectionModel().getSelectedItem().toString();
      Integer J = Integer.parseInt(output);
             E1.setSalle_id(I);
             E1.setEnseignant_id(J);
           
  
        int i=0 ;
    
     i = Integer.parseInt(I1.getText());
                  s.updateEmploi(i,I3.getText());
        data.clear();
        loadDataFromDatabase();
             
            return true;
    
    }
    
    public void fillcombox() 
    {
    try {
        List<String> idsalle =s.afficherIdSalle();
        salleid.setItems(FXCollections.observableArrayList(idsalle));
    } catch (SQLException ex) {
        Logger.getLogger(EMPLOI1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
        } 
        
      public void fillcombox1() 
    {
    try {
        List<String> idens =s.afficherIdEnesignant();
        enseignantid.setItems(FXCollections.observableArrayList(idens));
    } catch (SQLException ex) {
        Logger.getLogger(EMPLOI1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
        } 
       @FXML
    private void Chercher(ActionEvent event) throws SQLException {
              serviceEmploi s = new  serviceEmploi () ;
          int i=0 ;
    
     i = Integer.parseInt(idrecherche.getText());
     
          s.getemploiById(i);
                  LIBELLEA.setText(s.getemploiById(i).toString());
                  

    }
    private void setcellValue() {
    
         list.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             E1 = list.getItems().get(list.getSelectionModel().getSelectedIndex());
        String con_id = Integer.toString(E1.getSalle_id());
        String en_id = Integer.toString(E1.getEnseignant_id());


     String ci = Integer.toString(E1.getId());
        I1.setText(ci);
        
        I3.setText(E1.getDescription());
        
        
       
                
         
         });
    }
}
