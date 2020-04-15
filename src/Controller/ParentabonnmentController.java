/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Abonnement;
import Service.Parent_service;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class ParentabonnmentController implements Initializable {
    Abonnement ab;
    Parent_service abn= new Parent_service();
    @FXML
    private TableView<Abonnement> table_prt_abn;
    private ObservableList<Abonnement> data;
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, String> desc;
    @FXML
    private TableColumn<Abonnement, String> type;
    @FXML
    private TableColumn<Abonnement, String> dat_deb;
    @FXML
    private TableColumn<Abonnement, String> date_fin;
    @FXML
    private TableColumn<Abonnement, Float> total;
    @FXML
    private TableColumn<Abonnement, Integer> enf_id;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         data = FXCollections.observableArrayList();
        loadDataFromDatabase();
        setsCllTable();
    }    

    @FXML
    private void OnDelete(ActionEvent event) {
                
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setTitle("Confirmation Dialog");
	alert.setHeaderText(null);
	alert.setContentText("voulez-vous supprimmer cette abonnement ?");
	Optional<ButtonType> action = alert.showAndWait();		
        if(action.get() == ButtonType.OK)
            {
       ab = table_prt_abn.getItems().get(table_prt_abn.getSelectionModel().getSelectedIndex());
        abn.supprimer_abonnement_prt(ab);
        id.setText("");
        //enf_id.setText("");
        desc.setText("");
        total.setText("");
        type.setText("");
        dat_deb.setText("");
        date_fin.setText("");
        data.clear();
        loadDataFromDatabase();
                }
    }
    private void setsCllTable() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        dat_deb.setCellValueFactory(new PropertyValueFactory<>("data_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        enf_id.setCellValueFactory(new PropertyValueFactory<>("enf_id"));

    }
 
  private void loadDataFromDatabase() {
       List<Abonnement> lisabn =abn.afficher_abn();
        for(Abonnement e:lisabn){
            data.add(e);
        }
           table_prt_abn.setItems(data);
    }
}
