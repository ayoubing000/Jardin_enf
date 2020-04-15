/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Abonnement;
import Entity.Enfant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author ayoub
 */
public class Purchase_Service {
      private Connection con;
    private Statement ste;

    public Purchase_Service () {
        con = ConnexionBD.getInstancebd().getConnexionBD();
          try {
              ste = con.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(Purchase_Service.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
    public List<String> afficherEnfant() throws SQLException  {
     List<String> ls =new ArrayList<String>();
            PreparedStatement pt = con.prepareStatement("select id from enfant where matricul_prt=1");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
               // Enfant p = new Enfant(rs.getString("nom"));
                ls.add(rs.getString("id"));
            }
            
    return ls; 
    }   
    
 public void mnthoneAbonnement(Abonnement A) throws SQLException {
//String requeteInsert = "INSERT INTO `abonnement` (`enf_id`,`data_debut`,`date_fin`,`type`,`description`,`statu`,`statu_paiment`) VALUES(?,?,?,?,?,?,?)";
//PreparedStatement st = con.prepareStatement("insert into abonnement (enf_id,data_debut,date_fin,type,description,statu,statu_paiment) values (?,?,?,?,?,?,?,?)");
      String requeteInsert = "INSERT INTO `jardin`.`abonnement` (`id`,`enf_id`,  `data_debut`, `date_fin`,  `type`,`description`, `statu`, `statu_paiment`,`total`) VALUES ( '" + A.getId()+  "','" + A.getEnf_id()+  " ','"  + A.getData_debut()+  "',CURDATE()+ interval 1 month,'" + A.getType()+  "','" + A.getDescription()+"','" + A.getStatu()+"','" + A.getStatu_paiment()+"','" + A.getTotal()+"');";
            ste.executeUpdate(requeteInsert);
  
    }
  public void monthtwoAbonnement(Abonnement A) throws SQLException {
//String requeteInsert = "INSERT INTO `abonnement` (`enf_id`,`data_debut`,`date_fin`,`type`,`description`,`statu`,`statu_paiment`) VALUES(?,?,?,?,?,?,?)";
//PreparedStatement st = con.prepareStatement("insert into abonnement (enf_id,data_debut,date_fin,type,description,statu,statu_paiment) values (?,?,?,?,?,?,?,?)");
      String requeteInsert = "INSERT INTO `jardin`.`abonnement` (`id`,`enf_id`,  `data_debut`, `date_fin`,  `type`,`description`, `statu`, `statu_paiment`,`total`) VALUES ( '" + A.getId()+  "','" + A.getEnf_id()+  " ','"  + A.getData_debut()+  "',CURDATE()+ interval 3 month,'" + A.getType()+  "','" + A.getDescription()+"','" + A.getStatu()+"','" + A.getStatu_paiment()+"','" + A.getTotal()+"');";
            ste.executeUpdate(requeteInsert);
  
    }
   public void monthrheeeAbonnement(Abonnement A) throws SQLException {
//String requeteInsert = "INSERT INTO `abonnement` (`enf_id`,`data_debut`,`date_fin`,`type`,`description`,`statu`,`statu_paiment`) VALUES(?,?,?,?,?,?,?)";
//PreparedStatement st = con.prepareStatement("insert into abonnement (enf_id,data_debut,date_fin,type,description,statu,statu_paiment) values (?,?,?,?,?,?,?,?)");
      String requeteInsert = "INSERT INTO `jardin`.`abonnement` (`id`,`enf_id`,  `data_debut`, `date_fin`,  `type`,`description`, `statu`, `statu_paiment`,`total`) VALUES ( '" + A.getId()+  "','" + A.getEnf_id()+  " ','"  + A.getData_debut()+  "',CURDATE()+ interval 6 month,'" + A.getType()+  "','" + A.getDescription()+"','" + A.getStatu()+"','" + A.getStatu_paiment()+"','" + A.getTotal()+"');";
            ste.executeUpdate(requeteInsert);
  
    }
}
