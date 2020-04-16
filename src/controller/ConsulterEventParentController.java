/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import IServices.ServiceCommentaire;
import IServices.ServiceEvenement;
import IServices.ServiceInscriptionEvenement;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import entites.Commentaire;
import entites.Evenement;
import entites.InscriptionEvenement;
import entites.user;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.ServiceCommentaireImpl;
import services.ServiceInscriptionEvenementImpl;
import services.ServiceEvenementImpl;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class ConsulterEventParentController implements Initializable {

    @FXML
    private TableView<Evenement> tableEvent;
    @FXML
    private TableColumn<Evenement, String> nomEvent;
    @FXML
    private TableColumn<Evenement, String> descriptionEvent;
    @FXML
    private TableColumn<Evenement, String> typeEvent;
    @FXML
    private TableColumn<Evenement, Integer> placeRestante;

    @FXML
    private Label nomLabel;
//    @FXML
//    private Label descriptionLabel;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private TextField search;
    @FXML
    private Button btnParticiper;
    @FXML
    private Button btnAnnuler;

    private final ServiceInscriptionEvenement serviceInscription = new ServiceInscriptionEvenementImpl();
    private final ServiceEvenement serviceEvenement = new ServiceEvenementImpl();
    private final ServiceCommentaire serviceCommentaire = new ServiceCommentaireImpl();

    private Evenement selectedEvenement;
    private List<InscriptionEvenement> selectedEvenementInscriptions = new ArrayList<>();
    private List<Commentaire> selectedEvenementCommentaires = new ArrayList<>();

    private user user = new user(43, "parent"); 
    //private user user = new user(50, "ons");
    //private user user = new user(51, "haithem");

    List<Evenement> evenements = serviceEvenement.findAll();
    @FXML
    private Button btnCommenter;
    @FXML
    private AnchorPane commentsPane;
    @FXML
    private VBox commentsBox;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label imageLabel;
    @FXML
    private TextArea commentField;
    @FXML
    private TableColumn<?, ?> colNbrPlace;
    @FXML
    private Button btnFacbook;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.hideEvenementDetails();
        this.initTableCols();
        this.displayTableEvenements();
        addTableSelectionListener();
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            this.filterTableData(newValue);
        });
    }

    private void displayTableEvenements() {
        evenements = serviceEvenement.findAllwithInscriptionsCount();
        ObservableList<Evenement> obsList = FXCollections.observableArrayList(evenements);
        tableEvent.setItems(obsList);

    }

    private void initTableCols() {
        nomEvent.setCellValueFactory(new PropertyValueFactory<>("titre"));
        typeEvent.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionEvent.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNbrPlace.setCellValueFactory(new PropertyValueFactory<>("nombreDePlace"));
        placeRestante.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Evenement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Evenement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNombreDePlace() - param.getValue().getNbrInscriptions());
            }
        });

    }

    public void filterTableData(String filterText) {
        ObservableList<Evenement> obList = FXCollections.observableArrayList(evenements);
        FilteredList<Evenement> filteredData = new FilteredList<>(obList);

        filteredData.setPredicate(evenement -> {
            if (filterText == null || filterText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = filterText.toLowerCase();
            if (evenement.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            }
            return false; // Does not match.
        });
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableEvent.comparatorProperty());
        tableEvent.setItems(sortedData);
    }

    @FXML
    private void participer(ActionEvent event) {
        if (selectedEvenement.valid() == null) {
            int id = selectedEvenement.getId();
            InscriptionEvenement inscription = new InscriptionEvenement();
            inscription.setUser(this.user);
            inscription.setEvenement(selectedEvenement);
            inscription.setDateCreation(new Date(Calendar.getInstance().getTimeInMillis()));
            this.serviceInscription.saveInscription(inscription);
            this.setSelectedEvenement(selectedEvenement);

        }
    }

    @FXML
    private void annule(ActionEvent event) throws SQLException {
        if (selectedEvenement.valid() == null) {
            int id = selectedEvenement.getId();
            InscriptionEvenement inscription = selectedEvenementInscriptions
                    .stream()
                    .filter(i -> i.getUser().getId() == this.user.getId())
                    .findFirst()
                    .get();
            serviceInscription.delete(inscription);
            this.setSelectedEvenement(selectedEvenement);
        }
    }

    private void addTableSelectionListener() {
        tableEvent.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            Evenement evenement = tableEvent.getItems().get(tableEvent.getSelectionModel().getSelectedIndex());
            setSelectedEvenement(evenement);
        });
    }

    ObservableList<String> commentsObsList;

    public String mapComment(Commentaire c) {
        return c.getUser().getUserName() + ":\n" + c.getContenu() + "\nLe " + c.getCreation_date() + "\n\n";
    }

    public void setSelectedEvenement(Evenement evenement) {
        selectedEvenement = evenement;
        selectedEvenementInscriptions = serviceInscription.findInscriptionsByEvenementId(evenement.getId());
        selectedEvenementCommentaires = serviceCommentaire.findByEvenement(evenement);

        commentsObsList = FXCollections.observableArrayList();
        selectedEvenementCommentaires.stream().forEach(c -> {
            commentsObsList.add(mapComment(c));
        });

        ListView<String> list = new ListView<String>(commentsObsList);
        commentsBox.getChildren().clear();
        commentsBox.getChildren().addAll(list);

        if (selectedEvenement.valid() == null) {
            int id = selectedEvenement.getId();
            nom.setText(selectedEvenement.getTitre());
            description.setText(selectedEvenement.getDescription());
            image.setImage(new Image("http://localhost/jardin/web/uploads/events_photos/" + selectedEvenement.getImage()));
        }
        this.showEvenementDetails();

        boolean isAlreadySubscribed = selectedEvenementInscriptions.stream().anyMatch(inscription -> inscription.getUser().getId() == this.user.getId());
        boolean isFull = selectedEvenement.getNombreDePlace() <= selectedEvenement.getNbrInscriptions();
        this.btnParticiper.setDisable(isAlreadySubscribed || isFull);
        this.btnAnnuler.setDisable(!isAlreadySubscribed);

    }

    private void showEvenementDetails() {
        nomLabel.setVisible(true);
        nom.setVisible(true);
        description.setVisible(true);
        descriptionLabel.setVisible(true);
        imageLabel.setVisible(true);
        btnAnnuler.setVisible(true);
        btnParticiper.setVisible(true);
        image.setVisible(true);
        this.commentsPane.setVisible(true);

    }

    private void hideEvenementDetails() {
        nomLabel.setVisible(false);
        nom.setVisible(false);
        description.setVisible(false);
        descriptionLabel.setVisible(false);
        imageLabel.setVisible(false);
        btnAnnuler.setVisible(false);
        btnParticiper.setVisible(false);
        image.setVisible(false);
        this.commentsPane.setVisible(false);
    }

    @FXML
    private void saveComment(ActionEvent event) {
        String contenu = this.commentField.getText();
        Commentaire comment = new Commentaire();
        comment.setUser(this.user);
        comment.setContenu(contenu);
        comment.setCreation_date(new Date(Calendar.getInstance().getTimeInMillis()));
        comment.setEvenement(selectedEvenement);
        try {
            serviceCommentaire.save(comment);
            this.commentsObsList.add(mapComment(comment));
            this.commentField.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* @FXML
    private void btnFacbook(ActionEvent event) {
        
        
        
        String acessToken="EAACoe2srH2wBAGoytZA1GLk8e54zmgTlu0oJxA3yexdSio58xMwSBAIGA9eSRdy2m8On9uf0HMgkEsop3Ds9ZCuvBgnniDV8Kp0oq7HCI3uJNqvYcfNxuwXzygO6qLYMgcuABHo7Kx3wObN9daes7EfdD8lyeabYbVspovngvuac9sJDfrKJqrDbZCwZAZBjEpdM5NfRxoAZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(acessToken);
         FacebookType response =fbClient.publish( "me/feed", FacebookType.class,Parameter.with("message", "java fx api test"));
        System.out.println("fb.com/"+ response.getId()); 
   }*/

}
