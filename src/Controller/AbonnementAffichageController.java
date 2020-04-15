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
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class AbonnementAffichageController  implements Initializable  {
    AbonnementAd ab;
    Abonnement_adminService abn= new Abonnement_adminService();

    private TextField id;
    @FXML
    private TextArea desc;
    @FXML
    private TextField prix;
    @FXML
    private TextField type;
    @FXML
    private Button Ajouter;
    @FXML
    private TableColumn<AbonnementAd , Integer> column_id;
    @FXML
    private TableColumn<AbonnementAd, String> column_dsc;
    @FXML
    private TableColumn<AbonnementAd, String> column_ty;
    @FXML
    private TableColumn<AbonnementAd, Float> column_prx;
    @FXML
    private TextField rech;
    @FXML
    private TableView<AbonnementAd> tableabn;
    private ObservableList<AbonnementAd> data;
    @FXML
    private Button update;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
      Abonnement_adminService fs = new Abonnement_adminService();
      AbonnementAd f = new AbonnementAd();
      data = FXCollections.observableArrayList();
      loadDataFromDatabase();
       setsCllTable();
       setcellValue();

         /////////// recherche 
       FilteredList<AbonnementAd> FilteredData= new FilteredList<>(data , e-> true);
       rech.setOnKeyReleased(e->{
           rech.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                   FilteredData.setPredicate((Predicate<? super AbonnementAd>) film -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                   String idx = Integer.toString(film.getId());
 
                    if ((film.getDescription().contains(newValue)) || (film.getType().toLowerCase().contains(newValue))){
                        return true;
                    }
                    else if(idx.contains(newValue)){
                        return true ;
                    }
                   
                  
                    return false;

           });
       });
           SortedList<AbonnementAd> SortedData = new SortedList<>(FilteredData)  ; 
                    SortedData.comparatorProperty().bind(tableabn.comparatorProperty());
                    tableabn.setItems(SortedData);
 }); ///////////////////////////fin recherche

    }    


 private void setsCllTable() {
        
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
     private void setcellValue() {
    
         tableabn.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
             ab = tableabn.getItems().get(tableabn.getSelectionModel().getSelectedIndex());
             String parsePrice = Float.toString(ab.getPrix());
              //id.setText(ids);
              desc.setText(ab.getDescription());
              prix.setText(parsePrice);
              type.setText(ab.getType());
             
         });
    }

    @FXML
    private void Updata_data(ActionEvent event) {
        ab.setDescription(desc.getText());
        Float parsePrice = Float.parseFloat(prix.getText());
        ab.setPrix(parsePrice);
        ab.setType(type.getText());
        abn.modifierAbonnementAd(ab);
        data.clear();
        loadDataFromDatabase();
    }

    @FXML
    private void delete_data(ActionEvent event) {
        
    	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirmation Dialog");
	alert.setHeaderText(null);
	alert.setContentText("voulez-vous supprimmer cette abonnement ?");
	Optional<ButtonType> action = alert.showAndWait();		
        if(action.get() == ButtonType.OK)
            {
        abn.supprimerAbonnementAd(ab);
        desc.setText("");
        prix.setText("");
        type.setText("");
        data.clear();
        loadDataFromDatabase();
                }
    }

    @FXML
    private void cree(ActionEvent event)throws SQLException {
        //int value = (int) ab.getPrix();
        //Float value = Float.parseFloat(prix.getText());
        if (desc.getText().length()== 0 || type.getText().length()==0 || !isNumber(prix.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Tous les champs sont obligatoires");
                alert.setHeaderText("Tous les champs sont obligatoires");
                alert.setContentText("Tous les champs sont obligatoires");
                alert.showAndWait();
        }
        else {
            Abonnement_adminService s= new  Abonnement_adminService() ;
            AbonnementAd ab = new AbonnementAd();           
            ab.setDescription(desc.getText());
            Float pr = Float.parseFloat(prix.getText());
            ab.setPrix(pr);
            ab.setType(type.getText());
            s.creerAbonnementAd(ab);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success :) ");
            alert.setHeaderText("Ajouter Avec success ");
            alert.showAndWait();
            data.clear();
            loadDataFromDatabase();
        }
    }

    public static boolean isNumber(String text) {
        if (text.matches("^[0-9]*$")&& text.length()> 0) {
            return true;
        } else {
            return false;
        }

    }
    
                  

}
