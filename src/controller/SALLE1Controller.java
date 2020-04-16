/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.salle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.serviceSalle;

/**
 * FXML Controller class
 *
 * @author souhaib
 */
public class SALLE1Controller implements Initializable {

     serviceSalle s = new  serviceSalle() ;
    salle sa = new salle ();
    @FXML
    private TextField ID;
    @FXML
    private TextField LIBELLE;
    private TextField EMPLOIID;
    private Label labelle_affiche;
    @FXML
    private Text LIBELLEA;
    @FXML
    private TableView<salle> list;
    @FXML
    private TableColumn<salle, Integer> id;
    @FXML
    private TableColumn<salle, String> libelle;
    @FXML
    private TableColumn<salle, Integer> emploi;
    private ObservableList<salle> data;
    @FXML
    private ComboBox<String> idemploi;
    @FXML
    private TextField idrecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   data = FXCollections.observableArrayList();
                         loadDataFromDatabase();
       setsCllTable();
       fillcombox();
       setcellValue();

    }    

    @FXML
    private void AJOUTER(ActionEvent event) throws SQLException {
       if (ID.getText().length() == 0 || LIBELLE.getText().length() == 0  ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();  
       }
          else{
     serviceSalle s = new  serviceSalle() ;
    salle sa = new salle ();
    int i=0 ;
   
     i = Integer.parseInt(ID.getText());
     sa.setId(i);
    sa.setLibelle(LIBELLE.getText());
   String output =  idemploi.getSelectionModel().getSelectedItem().toString();
             Integer J = Integer.parseInt(output);

      sa.setEmploi_id(J);
     
     
    s.ajouterSalle(sa);
    data.clear();
            loadDataFromDatabase();

        }
    }
    
 
  
private void setsCllTable() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        emploi.setCellValueFactory(new PropertyValueFactory<>("emploi_id"));
    }
 
  private void loadDataFromDatabase() {
       List<salle> lisabn =s.afficherSalle();
        for(salle e:lisabn){
            System.out.println(e);
            data.add(e);
        }
           list.setItems(data);
    }  

      @FXML
    private void supprimer(ActionEvent event)throws SQLException, IOException {
          try {
               s.deleteSalle(sa);
          
               data.clear();
               loadDataFromDatabase();
           } catch (SQLException ex) {
               Logger.getLogger(SALLE1Controller.class.getName()).log(Level.SEVERE, null, ex);
           }
       
    }
    

    @FXML
    private boolean modifier(ActionEvent event)throws SQLException, IOException {
    

        
        sa.setLibelle(libelle.getText());
      
      String output =  idemploi.getSelectionModel().getSelectedItem().toString();

      Integer J = Integer.parseInt(output);
             sa.setEmploi_id(J);
           
  
        int i=0 ;
    
     i = Integer.parseInt(ID.getText());
                  s.updateSalle(i,LIBELLE.getText(),J);
        data.clear();
        loadDataFromDatabase();
             
            return true;
    }
     public void fillcombox() 
    {
    try {
        List<String> idsalle =s.afficherIdEmploi();
        idemploi.setItems(FXCollections.observableArrayList(idsalle));
    } catch (SQLException ex) {
        Logger.getLogger(EMPLOI1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
        } 

    @FXML
    private void Chercher(ActionEvent event) throws SQLException {
              serviceSalle s = new  serviceSalle() ;
          int i=0 ;
    
     i = Integer.parseInt(idrecherche.getText());
     
          s.getsalleById(i);
                  LIBELLEA.setText(s.getsalleById(i).toString());
                  

    }
     private void setcellValue() {
    
         list.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             sa = list.getItems().get(list.getSelectionModel().getSelectedIndex());
       
        String en_id = Integer.toString(sa.getEmploi_id());
 String ci = Integer.toString(sa.getId());
        ID.setText(ci);

            LIBELLE.setText(sa.getLibelle());
            
        
    
                
         
         });
    }
}
