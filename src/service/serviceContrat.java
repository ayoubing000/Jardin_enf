/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author souhaib
 */
import Iservice.IserviceContrat;
import entites.contrat;
import entites.salle;
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
import java.util.stream.Collectors;
import javax.swing.text.html.parser.DTDConstants;
import static jdk.nashorn.internal.runtime.Debug.id;
import utils.DataBase;

/**
 *
 * @author souhaib
 */
public  class serviceContrat implements IserviceContrat<contrat>{
    
private Connection con;
    private Statement ste;

    public serviceContrat() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouterContrat(contrat t) throws SQLException {
     ste = con.createStatement();
        String requeteInsert = "INSERT INTO `jardin`.`contrat` (`id`,`type`, `date_signature`, `date_debut`, `date_fin`, `date_resiliation`, `enseignant_id`) VALUES ( '" + t.getId()+  "','" + t.getType()+  " ','" + t.getDate_signature()+ "', '" + t.getDate_debut()+  "','" + t.getDate_fin()+  "','" + t.getDate_resiliation()+"','" + t.getEnseignant_id()+  "');";
        ste.executeUpdate(requeteInsert);
    }

   
    public void deleteContrat(contrat t) throws SQLException {
      String querry = "DELETE FROM contrat WHERE id =?";
        PreparedStatement d = con.prepareStatement(querry);
        d.setInt(1, t.getId());
        d.executeUpdate();
        System.out.println("contrat  supprimer");
    }

    public void updateContrat(int id ,String type) throws SQLException {
      PreparedStatement pla = con.prepareStatement("update contrat set   type=? where id=?");
        pla.setString(1, type);
        pla.setInt(2, id);
    
        pla.executeUpdate();

    }

    
  
    public List<contrat> afficherContrat()  {
   
  List<contrat> ls =new ArrayList<contrat>();
  try{
            PreparedStatement pt = con.prepareStatement("select * from contrat");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                contrat p = new contrat(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getDate(5), rs.getDate(6),rs.getInt(7));
                ls.add(p);
            }
            
            for (contrat p : ls){
                System.out.println(p.toString());
           }
    }
    catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
                return   ls; 
    }
       @Override
     public contrat getContratByType(String type) throws SQLException {
         
        String querry = "SELECT * FROM `contrat` WHERE `type` = 'type'";
        ste = con.createStatement();
        ResultSet rst = ste.executeQuery(querry);
        contrat a = new contrat();
        if (rst.next()) {
             a.setType(rst.getString("type"));
            a.setDate_signature(rst.getDate("Date_signature"));
            a.setDate_debut(rst.getDate("Date_debut"));
            a.setDate_fin(rst.getDate("Date_fin"));
            a.setDate_resiliation(rst.getDate("Date_resiliation"));
             a.setId(rst.getInt("id"));

}
        else 
        {System.out.println("contrat n'existe pas");}
        return a;
     }

@Override
     public contrat getcontratById(int id) throws SQLException {
        String querry = "SELECT * FROM `contrat` WHERE `id` = " + id + "";
        ste = con.createStatement();
        ResultSet rst = ste.executeQuery(querry);
       contrat a = new contrat();
        if (rst.next()) {
          a.setType(rst.getString("type"));
            a.setDate_signature (rst.getDate("Date_signature"));
            a.setDate_debut(rst.getDate("Date_debut"));
            a.setDate_fin(rst.getDate("Date_fin"));
            a.setDate_resiliation(rst.getDate("Date_resiliation"));
                      a.setEnseignant_id(rst.getInt("enseignant_id"));

          a.setId(id);
          System.out.println(a.toString());
          
        }else{System.out.println(" contrat n'existe pas ");}
        return a; 
    } 
    public void updateContrat(contrat t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  



    @Override
    public List<contrat> affichierContrat() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   public List<String> afficherIdEnesignant() throws SQLException  {
     List<String> ls =new ArrayList<String>();
            PreparedStatement pt = con.prepareStatement("select id from enseignant");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                ls.add(rs.getString("id"));
            }
            
    return ls; 
    } 
  
   


    
}
  