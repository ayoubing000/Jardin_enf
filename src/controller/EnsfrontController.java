/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.contrat;
import entites.emploi;
import entites.enseignant;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.FRONTSERVICE;
import service.serviceContrat;
import service.serviceEmploi;
import service.serviceEnseignant;

/**
 * FXML Controller class
 *
 * @author souhaib
 */
public class EnsfrontController implements Initializable {
    
    FRONTSERVICE  fron= new  FRONTSERVICE () ;
      emploi em = new emploi();
    contrat co = new contrat();
      enseignant en = new enseignant();
 
            private ObservableList<contrat> data;
             private ObservableList<enseignant> dataa;
             private ObservableList<emploi> dataaa;
 
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
    private TableView<contrat> list1;
    @FXML
    private TableColumn<contrat, Integer> id1;
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
    private TableView<emploi> list2;
    @FXML
    private TableColumn<emploi, Integer> id2;
    @FXML
    private TableColumn<emploi, Date> jour;
    @FXML
    private TableColumn<emploi, String> desc;
    @FXML
    private TableColumn<emploi, Integer> ensei;
    @FXML
    private TableColumn<emploi, Integer> salle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        data = FXCollections.observableArrayList();
                         loadDataFromDatabase();
                         dataa = FXCollections.observableArrayList();
                         loadDataFromDatabasee();
                          dataaa = FXCollections.observableArrayList();
                         loadDataFromDatabaseee();
        setsCllTableCONTRAT();
        setsCllTableEnseignant() ;
        setsCllTableEMPLOI();
        
         }  
         private void setsCllTableCONTRAT() {
        
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date_signature.setCellValueFactory(new PropertyValueFactory<>("date_signature"));
         date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        date_resiliation.setCellValueFactory(new PropertyValueFactory<>("date_resiliation"));
       enseigniant.setCellValueFactory(new PropertyValueFactory<>("enseignant_id"));
        
    }
 
  private void loadDataFromDatabase() {
       List<contrat> lisabn =fron.affichierContratFront();
        for(contrat e:lisabn){
            System.out.println(e);
            data.add(e);
        }
           list1.setItems(data);
    } 
    private void setsCllTableEnseignant() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cv.setCellValueFactory(new PropertyValueFactory<>("cv"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        diplomes.setCellValueFactory(new PropertyValueFactory<>("diplomes"));
          CONTRAT.setCellValueFactory(new PropertyValueFactory<>("contrats_id"));
        EMPLOI.setCellValueFactory(new PropertyValueFactory<>("emplois_id"));  
    }
 
  private void loadDataFromDatabasee() {
       List<enseignant> lisabn =fron.afficherEnseignantFront();
        for(enseignant e:lisabn){
            System.out.println(e);
            dataa.add(e);
        }
           list.setItems(dataa);
    } 

        private void setsCllTableEMPLOI() {
        
        id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        jour.setCellValueFactory(new PropertyValueFactory<>("jour"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
         ensei.setCellValueFactory(new PropertyValueFactory<>("enseignant_id"));
       salle.setCellValueFactory(new PropertyValueFactory<>("salle_id"));
        
    }
 
  private void loadDataFromDatabaseee() {
       List<emploi> lisabn =fron.afficherEmploiFront();
        for(emploi e:lisabn){
            System.out.println(e);
            dataaa.add(e);
        }
           list2.setItems(dataaa);
    }
    
    
    
}
     
