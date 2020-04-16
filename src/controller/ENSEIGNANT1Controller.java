/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.enseignant;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.serviceEnseignant;

/**
 * FXML Controller class
 *
 * @author souhaib
 */
public class ENSEIGNANT1Controller implements Initializable {

       serviceEnseignant s= new  serviceEnseignant() ;
      enseignant E1 = new enseignant();

    @FXML
    private TextField I1;
    @FXML
    private TextField I2;
    @FXML
    private TextField I5;
    @FXML
    private TextField I4;
    @FXML
    private TextField I6;
    private TextField I7;
    @FXML
    private TextField I3;
    private TextField I8;
    private Text textaffiche;
    private TableColumn<salle, Integer> emploi;
    private ObservableList<enseignant> data;
    @FXML
    private TableView<enseignant> list;
    @FXML
    private TableColumn<enseignant, Integer> id;
    @FXML
    private TableColumn<enseignant, Integer> cin;
    @FXML
    private TableColumn<enseignant, String> cv;
    @FXML
    private TableColumn<enseignant, String> email;
    @FXML
    private TableColumn<enseignant, String> password;
    @FXML
    private TableColumn<enseignant, String> diplomes;
   
    @FXML
    private TableColumn<enseignant, Integer> CONTRAT;
    @FXML
    private TableColumn<enseignant, Integer> EMPLOI;
    @FXML
    private ComboBox<String> contratid;
    @FXML
    private ComboBox<String> emploiid;
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
       fillcombox() ;
       fillcombox1() ;
       setcellValue();

    }    
    public static boolean iscin(String I2) {



        if (I2.matches("^[0-9]+$")&& I2.length()== 8) {

            return true;

        } else {

            return false;

        }

    }
    public static boolean isID(String I1) {



        if (I1.matches("^[0-9]+$")&& I1.length()>0) {

            return true;

        } else {

            return false;

        }

    }

    @FXML
    private void AJOUTER(ActionEvent event)throws SQLException {
               if (!isID(I1.getText()) || !iscin(I2.getText()) || I3.getText().length() == 0 || I4.getText().length() == 0 || I5.getText().length() == 0|| I6.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();  
       }
               else{
         serviceEnseignant s= new  serviceEnseignant() ;
      enseignant E1 = new enseignant();
            int i=0 ;
            int j=0;
            int h =0 ;
     i = Integer.parseInt(I1.getText());
     E1.setId(i);
      i = Integer.parseInt(I2.getText());
     E1.setCin(i);
    
    E1.setCv(I3.getText());
    E1.setEmail(I4.getText());
    E1.setPassword(I5.getText());
    E1.setDiplomes(I6.getText());
          String output =  contratid.getSelectionModel().getSelectedItem().toString();

      Integer J = Integer.parseInt(output);
             E1.setContrats_id(J);
               String outputt =  emploiid.getSelectionModel().getSelectedItem().toString();
             Integer H= Integer.parseInt(outputt);
             E1.setEmplois_id(H);
  
    
    
     
    s.ajouterEnseignant(E1);
    data.clear();
            loadDataFromDatabase();

    
    }
    }
    private void Afficher(ActionEvent event) throws SQLException {
                      serviceEnseignant s= new  serviceEnseignant() ;

           textaffiche.setText(s.afficherEnseignant().toString());
    }
    private void setsCllTable() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cv.setCellValueFactory(new PropertyValueFactory<>("cv"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        diplomes.setCellValueFactory(new PropertyValueFactory<>("diplomes"));
          CONTRAT.setCellValueFactory(new PropertyValueFactory<>("contrats_id"));
        EMPLOI.setCellValueFactory(new PropertyValueFactory<>("emplois_id"));  
    }
 
  private void loadDataFromDatabase() {
       List<enseignant> lisabn =s.afficherEnseignant();
        for(enseignant e:lisabn){
            System.out.println(e);
            data.add(e);
        }
           list.setItems(data);
    } 

    @FXML
    private void supprimer(ActionEvent event)throws SQLException, IOException {
       try {
               s.deleteEnseignant(E1);
            
               data.clear();
               loadDataFromDatabase();
           } catch (SQLException ex) {
               Logger.getLogger(ENSEIGNANT1Controller.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    

    @FXML
    private boolean modifier(ActionEvent event)throws SQLException, IOException {
      int i=0; 
      int j =0;
         
             j = Integer.parseInt(I1.getText());
        
            i = Integer.parseInt(I2.getText());
       
        E1.setCv(cv.getText());
        E1.setEmail(email.getText());
        E1.setPassword(password.getText());
        E1.setDiplomes(diplomes.getText());
                String output =  contratid.getSelectionModel().getSelectedItem().toString();

      Integer J = Integer.parseInt(output);
             E1.setContrats_id(J);
               String outputt =  emploiid.getSelectionModel().getSelectedItem().toString();
             Integer H= Integer.parseInt(outputt);
             E1.setEmplois_id(H);
  
        
        s.updateEnseignant(j, i, I3.getText(), I4.getText(), I5.getText(), I6.getText(),J,H);
        data.clear();
        loadDataFromDatabase();
             
            return true;
    }
      private void setcellValue() {
    
         list.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             E1 = list.getItems().get(list.getSelectionModel().getSelectedIndex());
        String cin = Integer.toString(E1.getCin());
        String con_id = Integer.toString(E1.getContrats_id());
        String en_id = Integer.toString(E1.getEmplois_id());
 String ci = Integer.toString(E1.getId());
        I1.setText(ci);

           
        I2.setText(cin);
        I3.setText(E1.getCv());
        I4.setText(E1.getEmail());
        I5.setText(E1.getPassword());
        I6.setText(E1.getDiplomes());
       
                
         
         });
    }
      
      public void fillcombox() 
    {
    try {
        List<String> idsalle =s.afficherIdcontrat();
        contratid.setItems(FXCollections.observableArrayList(idsalle));
    } catch (SQLException ex) {
        Logger.getLogger(EMPLOI1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
        } 
        
      public void fillcombox1() 
    {
    try {
        List<String> idens =s.afficherIdEmploi();
        emploiid.setItems(FXCollections.observableArrayList(idens));
    } catch (SQLException ex) {
        Logger.getLogger(EMPLOI1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
        } 

       @FXML
     private void Chercher(ActionEvent event) throws SQLException {
              serviceEnseignant s = new  serviceEnseignant();
          int i=0 ;
     i = Integer.parseInt(idrecherche.getText());
     
          s.getenseignantById(i);
                  LIBELLEA.setText(s.getenseignantById (i).toString());
                  

    }
}
