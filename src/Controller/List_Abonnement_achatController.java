/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AbonnementAd;
import Service.Abonnement_adminService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class List_Abonnement_achatController implements Initializable {

    @FXML
    private TableView<AbonnementAd> tableabn;
    private ObservableList<AbonnementAd> data;
    @FXML
    private TableColumn<AbonnementAd, String> column_dsc;
    @FXML
    private TableColumn<AbonnementAd, String> column_ty;
    @FXML
    private TableColumn<AbonnementAd, Float> column_prx;
    @FXML
    private Button brn_acheter;
    Abonnement_adminService abn= new Abonnement_adminService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      Abonnement_adminService fs = new Abonnement_adminService();
      AbonnementAd f = new AbonnementAd();
      data = FXCollections.observableArrayList();
      loadDataFromDatabase();
       setsCllTable();
    } 
     private void setsCllTable() {
        
        column_dsc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        column_prx.setCellValueFactory(new PropertyValueFactory<>("prix"));
        column_ty.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
 
  private void loadDataFromDatabase() {
       List<AbonnementAd> lisabn =abn.afficherAbonnementAd();
        for(AbonnementAd e:lisabn){
            System.out.println(e);
            data.add(e);
        }
           tableabn.setItems(data);
    }
  
  @FXML
    private boolean btn_acheter(ActionEvent event) {
         try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Purchase.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        PurchaseController upc = fxmlLoader.getController();
                        upc.TranslatingData(tableabn.getSelectionModel().getSelectedItem());
                        stage.setScene(new Scene(root));
                        stage.show();
                        } catch (IOException ex) {
                        Logger.getLogger(AbonnementAffichageController.class.getName()).log(Level.SEVERE, null, ex);
                           }  
         return true ;
    }

    
   
}
