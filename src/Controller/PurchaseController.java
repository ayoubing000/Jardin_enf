/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Abonnement;
import Entity.AbonnementAd;
import Service.Purchase_Service;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.lang3.time.DateUtils;


/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class PurchaseController implements Initializable {

    @FXML
     Button confirmer;
    @FXML
     Text desc_txt;
    @FXML
     Text type_txt;
    @FXML
     Label prix_txt;
    TextField desc;
    private AbonnementAd selectedS;
    Purchase_Service p = new Purchase_Service();
    @FXML
    private ComboBox<String> list_f;
    @FXML
    private ComboBox<String> month;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        fillcombox();
        fillmonths();

    }    

    @FXML
    private void confirm(ActionEvent event) throws ParseException {
        try {
            String output = list_f.getSelectionModel().getSelectedItem().toString();
            String typ = month.getSelectionModel().getSelectedItem().toString();
            if (typ == "" || output == "")
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Tous les champs sont obligatoires");
                alert.setHeaderText("Tous les champs sont obligatoires");
                alert.setContentText("Tous les champs sont obligatoires");
                alert.showAndWait();
            }
            else if (typ == "mensuel")
            {   
              Integer i = Integer.parseInt(output);
              java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
              Float prix = Float.parseFloat(prix_txt.getText());
               // Integer pr = Integer.parseInt(prix_txt.getText());
                Abonnement E1 = new Abonnement();
                E1.setEnf_id(i);
                E1.setData_debut(date);
                E1.setType(type_txt.getText());
                E1.setDescription(desc_txt.getText());
                E1.setTotal(prix*1);
                //E1.setDate_fin(date);
                p.mnthoneAbonnement(E1);
            }else if ( typ =="trimestriel")
            {
                Date newDate = DateUtils.addMonths(new Date(), 3);
                Date today = Calendar.getInstance().getTime();                 
                Integer i = Integer.parseInt(output);
                Integer pr = Integer.parseInt(prix_txt.getText());
                Abonnement E1 = new Abonnement();
                E1.setDescription(desc_txt.getText());
                E1.setType(type_txt.getText());
                E1.setTotal(pr*3);
                E1.setData_debut(today);
               // E1.setDate_fin(newDate);
                E1.setEnf_id(i);
                p.monthtwoAbonnement(E1);
            }else {
                Date newDate = DateUtils.addMonths(new Date(), 12);
                Date today = Calendar.getInstance().getTime();                 
                Integer i = Integer.parseInt(output);
                Integer pr = Integer.parseInt(prix_txt.getText());
                Abonnement E1 = new Abonnement();
                E1.setDescription(desc_txt.getText());
                E1.setType(type_txt.getText());
                E1.setTotal(pr*12);
                E1.setData_debut(today);
               // E1.setDate_fin(newDate);
                E1.setEnf_id(i);
                p.monthrheeeAbonnement(E1);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
   }
    

    
    
    public void TranslatingData(AbonnementAd m) {
        selectedS = m;
        desc_txt.setText(selectedS.getDescription());
        type_txt.setText(selectedS.getType());
        prix_txt.setText(String.valueOf(selectedS.getPrix()));
      
    }
    
    public void fillcombox() 
    {
        try {
            List<String> enf =p.afficherEnfant();
            list_f.setItems(FXCollections.observableArrayList(enf));
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void fillmonths()
    {
        month.getItems().addAll(
        "mensuel",
        "trimestriel",
        "semestriel"
        );

    }
 /*  
public Date  addMonths(String dateAsString, int nbMonths) throws ParseException {
        String format = "MM/dd/yyyy" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Date dateAsObj = sdf.parse(dateAsString) ;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateAsObj);
        cal.add(Calendar.MONTH, nbMonths);
        Date dateAsObjAfterAMonth = cal.getTime() ;
    System.out.println(sdf.format(dateAsObjAfterAMonth));
    return dateAsObjAfterAMonth ;
}*/
}
