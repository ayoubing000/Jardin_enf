/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Iservice.Iservice;
import entites.emploi;
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
import javax.swing.text.html.parser.DTDConstants;
import utils.DataBase;

/**
 *
 * @author souhaib
 */
public class serviceEmploi implements Iservice<emploi>{
    
private Connection con;
    private Statement ste;

    public serviceEmploi() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouterEmploi(emploi t) throws SQLException {
     ste = con.createStatement();
        String requeteInsert = "INSERT INTO `jardin`.`emploi` (`id`, `jour`, `description`, `enseignant_id`, `salle_id`) VALUES ( '" + t.getId()+  "', '" + t.getJour()+ "', '" + t.getDescription()+  "', '" + t.getEnseignant_id()+ "', '" + t.getSalle_id()+ "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public void deleteEmploi(emploi t) throws SQLException {
      String querry = "DELETE FROM emploi WHERE id =?";
        PreparedStatement d = con.prepareStatement(querry);
        d.setInt(1, t.getId());
        d.executeUpdate();
        System.out.println("testsup");
    }

    public void updateEmploi(int id ,String description) throws SQLException {
      PreparedStatement pla = con.prepareStatement("update emploi set   description=? where id=?");
        pla.setString(1, description);
        pla.setInt(2, id);
    
        pla.executeUpdate();

    }

    
  
    @Override
    public List<emploi> afficherEmploi()  {
   
  List<emploi> ls =new ArrayList<emploi>();
  try{
            PreparedStatement pt = con.prepareStatement("select * from emploi");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                emploi p = new emploi(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                ls.add(p);
            }
            
            for (emploi p : ls){
                System.out.println(p.toString());
            
            }
    }
    catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
                return   ls; 
    }
  

    @Override
    public void updateEmploi(emploi t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> afficherIdSalle() throws SQLException  {
     List<String> ls =new ArrayList<String>();
            PreparedStatement pt = con.prepareStatement("select id from salle");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                ls.add(rs.getString("id"));
            }
            
    return ls; 
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

       public emploi getemploiById(int id ) throws SQLException {
        String querry = "SELECT * FROM `emploi` WHERE `id` = " + id + "";
        ste = con.createStatement();
        ResultSet rst = ste.executeQuery(querry);
       emploi a = new emploi();
        if (rst.next()) {
            a.setDescription(rst.getString("description"));
          a.setJour(rst.getDate("jour"));
          a.setEnseignant_id(rst.getInt("enseignant_id"));
          a.setSalle_id(rst.getInt("salle_id"));
          

          a.setId(id);
          return a; 
          
        }
        else{System.out.println(" emploi n'existe pas ");
        }
        return a; 
       
    } 

    @Override
    public emploi getsalleById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

       

