/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Abonnement;
import Entity.AbonnementAd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.ConnexionBD;

/**
 *
 * @author ayoub
 */
public class Parent_service {
       private Connection con;
    private Statement ste;

    public Parent_service () {
                  try {

        con = ConnexionBD.getInstancebd().getConnexionBD();
          ste = con.createStatement();
            } catch (SQLException ex) {
            System.out.println(ex);
        }
      }
    
      public List<Abonnement> afficher_abn(){
      List<Abonnement> ls =new ArrayList<Abonnement>();
      String req2="select * from abonnement as ab INNER JOIN enfant as x ON ab.enf_id =x.id and x.matricul_prt=1 ";
      try {
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
                    Abonnement  f = new Abonnement();
                     // System.out.println(res.getInt("id"));
                      f.setId(res.getInt("id"));
                       f.setEnf_id(res.getInt("enf_id"));
                       f.setData_debut(res.getDate("Data_debut"));
                       f.setDate_fin(res.getDate("Date_fin"));
                      f.setDescription(res.getString("Description") );
                      f.setType(res.getString("type"));
                      f.setTotal(res.getFloat("total"));
                      ls.add(f);
     
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return ls;
    }
      
      public void supprimer_abonnement_prt(Abonnement A)  {
        try {
            String querry = "DELETE FROM abonnement WHERE id =?";
            PreparedStatement d = con.prepareStatement(querry);
            d.setInt(1, A.getId());
            d.executeUpdate();
            }
        catch (SQLException ex) {
                System.out.println(ex);
            }
    }
}
