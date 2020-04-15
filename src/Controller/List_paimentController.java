/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Abonnement;
import Service.Abonnemnt_Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class List_paimentController implements Initializable {
    Abonnement ab;
    Abonnemnt_Service abn= new Abonnemnt_Service();
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, Integer> enf_id;
    @FXML
    private TableColumn<Abonnement, Date> date_deb;
    @FXML
    private TableColumn<Abonnement, Date> date_fin;
    @FXML
    private TableColumn<Abonnement, String> typ;
    @FXML
    private TableColumn<Abonnement, String> desc;
    @FXML
    private TableColumn<Abonnement, String> statu;
    @FXML
    private TableColumn<Abonnement, String> statu_p;
    @FXML
    private TableColumn<Abonnement, Float> total;
    @FXML
    private Button payer;
    @FXML
    private TableView<Abonnement> tabl;
    private ObservableList<Abonnement> data;
    public static final String ACCOUNT_SID = "ACadb91706b63c9681875d8c4419471e8d";
    public static final String AUTH_TOKEN = "e5d75d486eb118880f9b0d5c57c02919";

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
    private void payer_abn(ActionEvent event) {
        ab = tabl.getItems().get(tabl.getSelectionModel().getSelectedIndex());
        if(ab.getStatu()=="valide")
        {
               Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText("Vous avez déja payer cette abonnement"+ab.getId());
                alert.setContentText("Vous avez déja payer cette abonnement"+ab.getId());
                alert.showAndWait();
        }else
        {
                abn.modifierstatu(ab.getId());
                sendphone();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("User saved successfully.");
                alert.setHeaderText(null);
                alert.setContentText("Un SMS envoyer avec succès.");
                alert.showAndWait();
                data.clear();
                loadDataFromDatabase();
        }
        
    }

    
    private void setsCllTable() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        enf_id.setCellValueFactory(new PropertyValueFactory<>("enf_id"));
        date_deb.setCellValueFactory(new PropertyValueFactory<>("data_debut"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        typ.setCellValueFactory(new PropertyValueFactory<>("type"));
        statu.setCellValueFactory(new PropertyValueFactory<>("statu"));
        statu_p.setCellValueFactory(new PropertyValueFactory<>("statu_paiment"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));


    }
 
  private void loadDataFromDatabase()  {
        try {
            List<Abonnement> lisabn =abn.afficherAbonnementPa();
            for(Abonnement e:lisabn){
                System.out.println(e);
                data.add(e);
            }
            tabl.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(List_paimentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  private void sendphone()
  {
       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       Message message = Message.creator(new PhoneNumber("+21627232362"), // to
                        new PhoneNumber("+16788057888"), // from
                        "Votre Abonnement est Payer")
                .create();
       System.out.println(message.getSid());

  }
}
