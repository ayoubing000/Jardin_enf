/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Abonnement;
import Iservice.Iabonnement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
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
public class Abonnemnt_Service implements Iabonnement  {
    private Connection con;
    private Statement ste;

    public Abonnemnt_Service (){
        try {
            con = ConnexionBD.getInstancebd().getConnexionBD();
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Abonnemnt_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

    @Override
    public void creerAbonnement(Abonnement A)  throws SQLException  {
      String requeteInsert = "INSERT INTO `jardin`.`abonnement` (`id`,`enf_id`,  `data_debut`, `date_fin`,  `type`,`description`, `statu`, `statu_paiment`) VALUES ( '" + A.getId()+  "','" + A.getEnf_id()+  " ','"  + A.getData_debut()+  "','" + A.getDate_fin()+  "','" + A.getType()+  "','" + A.getDescription()+"','" + A.getStatu()+"','" + A.getStatu_paiment()+"');";
            ste.executeUpdate(requeteInsert);
     
    }

    @Override
    public void modifierAbonnement(Abonnement A) throws SQLException   {
        PreparedStatement pla = con.prepareStatement("update abonnement set   type=? where id=?");
        pla.setString(1, A.getType());
        pla.setInt(2, A.getId());
        pla.executeUpdate();   
    }

    @Override
    public void supprimerAbonnement(Abonnement A) throws SQLException  {
        String querry = "DELETE FROM abonnement WHERE id =?";
        PreparedStatement d = con.prepareStatement(querry);
        d.setInt(1, A.getId());
        d.executeUpdate();

    }

    @Override
    public List<Abonnement> afficherAbonnementAd() throws SQLException  {
     List<Abonnement> ls =new ArrayList<Abonnement>();
            PreparedStatement pt = con.prepareStatement("select * from abonnement");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Abonnement p = new Abonnement(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getString(5),  rs.getString(6), rs.getString(7), rs.getString(8));
                ls.add(p);
            }
            
            for (Abonnement p : ls){
                System.out.println(p.toString());
            
     }
    return ls; 
    }   

  

    @Override
    public  void  RechercherAbonnementByNom(String nom_abonnement) throws SQLException {
         //ArrayList<Abonnement> listeArticle = new ArrayList<Abonnement>();
            Statement ps = con.createStatement();
            ResultSet res;
            res = ps.executeQuery("select * from abonnement where type like '%"+nom_abonnement+"%'");
            System.out.println(res.getRow());
            if(res.last())
            {

                int id = res.getInt("id");
                int id_enf =res.getInt("enf_id");
                Date data_debut  = res.getDate("data_debut");
                Date date_fin  = res.getDate("date_fin");
                String type=res.getString("type");
                String description=res.getString("description");
                String statu=res.getString("statu");
                String statu_paiment=res.getString("statu_paiment");
                Abonnement art = new Abonnement(id,id_enf , data_debut,date_fin ,type,description,statu,statu_paiment);   
                System.out.println(art.toString());
            
     }  
            else
                {
                        System.out.println("pas trouver");
                        }
    }
    public void onemonth(Abonnement A) throws SQLException{
        String requeteInsert = "INSERT INTO `jardin`.`abonnement` (`enf_id`,`data_debut`,`date_fin`,`type`,`description`,`statu`,`statu_paiment`) VALUES (?,?,current_date + interval 1 month,?,?,?,?)";
        ste.executeUpdate(requeteInsert);

    }
    
     public List<Abonnement> afficherAbonnementPa() throws SQLException  {
     List<Abonnement> ls =new ArrayList<Abonnement>();
            PreparedStatement pt = con.prepareStatement("select * from abonnement");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                Abonnement p = new Abonnement(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getString(5),  rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9));
                ls.add(p);
            }
            
            for (Abonnement p : ls){
                System.out.println(p.toString());
            
     }
    return ls; 
    }   

     public void modifierstatu(int id)  {
        try {
            PreparedStatement pla = con.prepareStatement("update abonnement set statu_paiment='Payer' , statu='Valide' where id=?");
            pla.setInt(1, id);   
            pla.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Abonnemnt_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
