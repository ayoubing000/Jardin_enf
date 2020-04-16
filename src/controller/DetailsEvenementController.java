/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import utlis.MaConnection;



/**
 * FXML Controller class
 *
 * @author ons
 */
public class DetailsEvenementController implements Initializable {

    @FXML
    private BarChart<String, Integer> BarChart;
    @FXML
    private Button load;
    @FXML
    private Button retour;

   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void load(ActionEvent event) {
       
        MaConnection myc = MaConnection.getInstance();
    Connection cnx=myc.getConnection();
    
          String requete;
       
     requete = "SELECT nombreDePlace, count(*)  FROM evenement GROUP BY nombreDePlace ";  
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
         
         
            try {
            PreparedStatement ps = cnx.prepareStatement(requete);
         
           ResultSet resultat = ps.executeQuery();
            
            while (resultat.next()){  
                series.getData().add(new XYChart.Data<>(resultat.getString(1),resultat.getInt(2)));
                 
            }
      BarChart.getData().add(series);
    }
            catch (SQLException ex) {
            
            System.out.println("erreur  " + ex.getMessage());
        }
            }
     @FXML
    private void retour(ActionEvent event) {
          try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("AjoutEvenement.fxml"));
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   
   
}
