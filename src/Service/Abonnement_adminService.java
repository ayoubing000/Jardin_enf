/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Abonnement;
import Entity.AbonnementAd;
import Iservice.IabonnementAd;
import java.sql.Connection;
import java.sql.Date;
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
public class Abonnement_adminService implements IabonnementAd{
     private Connection con;
    private Statement ste;

    public Abonnement_adminService () {
                  try {

        con = ConnexionBD.getInstancebd().getConnexionBD();
          ste = con.createStatement();
            } catch (SQLException ex) {
            System.out.println(ex);
        }
      }
    @Override
    public void creerAbonnementAd(AbonnementAd A) throws SQLException {
        String requeteInsert = "INSERT INTO `jardin`.`abonnmentad` (`Description`,  `Prix`, `type`) VALUES ('" + A.getDescription()+  " ','"  + A.getPrix()+  "','" + A.getType()+  "');";
        ste.executeUpdate(requeteInsert);    
    }

    @Override
    public void modifierAbonnementAd(AbonnementAd A){
         try {
        PreparedStatement pla = con.prepareStatement("update abonnmentad set  Description=? ,Prix =?, type=? where id="+A.getId()+"");
        pla.setString(1, A.getDescription());
        pla.setFloat(2, A.getPrix());
        pla.setString(3, A.getType());
        pla.executeUpdate();
         } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerAbonnementAd(AbonnementAd A)  {
           try {
        String querry = "DELETE FROM abonnmentad WHERE id =?";
        PreparedStatement d = con.prepareStatement(querry);
        d.setInt(1, A.getId());
        d.executeUpdate();
        System.out.println("testsup"); 
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<AbonnementAd> afficherAbonnementAd(){
      List<AbonnementAd> ls =new ArrayList<AbonnementAd>();
      AbonnementAd f = null ;
      String req2="select * from abonnmentad";
      try {
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) { 
                      f = new AbonnementAd();
                      f.setId(res.getInt("id"));
                      f.setDescription(res.getString("Description") );
                      f.setType(res.getString("type"));
                      f.setPrix(res.getFloat("prix"));
                      ls.add(f);
          }
          
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
        
     return ls;
    }

    @Override
    public void RechercherAbonnementAdId(int id_abonnement)throws SQLException {
            Statement ps = con.createStatement();
            ResultSet res;
            res = ps.executeQuery("select * from abonnmentad where id= "+id_abonnement+" ");
            System.out.println(res.getRow());
            if(res.last())
            {

                int id = res.getInt("id");
                String description =res.getString("description");
                float prix  = res.getFloat("prix");
                String type  = res.getString("type");
                AbonnementAd art = new AbonnementAd(id,description,prix,type);
                      
                System.out.println(art.toString());
            
     }  

            
             else
                {
                        System.out.println("pas trouver");
                        }
    }
    

   
    
}
