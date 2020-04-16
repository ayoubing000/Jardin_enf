/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souhaib;

import entites.contrat;
import entites.emploi;
import entites.enseignant;
import entites.salle;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.serviceContrat;
import service.serviceEmploi;
import service.serviceEnseignant;
import service.serviceSalle;

/**
 *
 * @author souhaib
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked me!");
       java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
       // emploi E = new emploi(51,date,"TEST",13,37);
          emploi E5 = new emploi(21,date,"HI",13,2);
        //  emploi E3 = new emploi(10,date,"FAST",6,6);
       //serviceEmploi s= new  serviceEmploi() ;
        
     //------------------------emploi---------------------------------
                         // ajout emploi
       
                 // s.deleteEmploi(E5);
         //s.ajouterEmploi(E);
        // s.ajouterEmploi(E5);
         //s.ajouterEmploi(E3);
        // s.ajouterEmploi(E5);
     
              // supprier
        //System.out.println("--------------------------");
      //  s.deleteEmploi(E);
        //System.out.println("--------------------------");
        
          // affichage
        //System.out.println("--------------------------");
        // s.afficherEmploi();
        
           //modification
           // s.updateEmploi(33,"TEST");
        //System.out.println("--------------------------");
        //s.afficherEmploi();
        //System.out.println("--------------------------");
        
        //------------------------e----contrat---------------------------
     //ajouter contrat
        //contrat p1 = new contrat(12,"yalla go",date ,date,date,date,1);
         //contrat p7 = new contrat(7,"SALAMOU3LIKOM",date ,date,date,date,1);
         //contrat p3 = new contrat(14,"CVP",date ,date,date,date,3);
         //contrat p8 = new contrat(8,"salamou3likom",date ,date,date,date,6);
            
       // serviceContrat s = new  serviceContrat() ;
     
          //       System.out.println("--------------------------");

            
         

         // s.ajouterContrat(p3);
      //
      
    //s.ajouterContrat(p8);   
        // 
        //s.ajouterContrat(p3);
   
           // supprier un contrat
        //System.out.println("--------------------------");
       // s.deleteContrat(p8);
        //System.out.println("------------le contrat est supprimé--------------");
          // affichage des contrats
       // System.out.println("--------------------------");
       //  s.afficherContrat();
            //modification des contrats
      // s.updateContrat(7,"OOOOOOOO");
      //  System.out.println("--------------------------");
       // s.afficherContrat();
        //System.out.println("-----------le contrat est modifie---------------");
                       //rechercher un contrat selon l id
                     
         
          //------------------------enseignant---------------------------------
                 // ajout enseignant
          //enseignant EN = new enseignant(13,13249005,"prof de calcul","sou@gmail.com","azerty","ingenieur",8,1);
          //enseignant EN1 = new enseignant(4,13265405,"prof de SPORT","aymen@gmail.com","azerty","licence",12,1);
          //enseignant EN2 = new enseignant(3,132243005,"prof de SC","mazen@gmail.com","azerty","doctora",8,11);

     //serviceEnseignant s= new  serviceEnseignant() ;
        
       // s.ajouterEnseignant(EN);
        // s.ajouterEnseignant(EN1);
     //    s.ajouterEnseignant(EN2);
       
     
              // supprier
        //System.out.println("--------------------------");
      // s.deleteEnseignant(EN2);
        // s.deleteEnseignant(EN1);
        //System.out.println("--------------------------");
        
          // affichage
        //System.out.println("--------------------------");
         //s.afficherEnseignant();
        
           //modification
           //s.updateEnseignant(6,12345667,"dclsd","qsdqd,","rgtz","dDSF");
        //System.out.println("--------------------------");
        //s.afficherEnseignant();
        //System.out.println("--------------------------");
        
        
                      // ajout salle
//:           salle sal= new salle(2,"salle num 2",11);
  //         salle sal1= new salle(9,"salle num 6",21);
    //       salle sal2= new salle(7,"salle num 3",11);
        salle sal3= new salle("salle num ",9);
         serviceSalle s = new  serviceSalle() ;
           System.out.println("--------------------------");
           //        s.getsalleById(9);
               
           // s.ajouterSalle(sal3);
        
      //  s.ajouterSalle(sal1);
         //s.ajouterSalle(sal2);
       s.ajouterSalle(sal3);
       
     
              // supprier
        //System.out.println("--------------------------");
       
       
      // s.deleteSalle(sal2);
        //System.out.println("--------------------------");
        
          // affichage
       // System.out.println("--------------------------");
         //s.afficherSalle();
        
           //modification
          // s.updateSalle(6,"HELLO");
        
       // System.out.println("--------------------------");
        //s.afficherSalle();
        //System.out.println("--------------------------");
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
