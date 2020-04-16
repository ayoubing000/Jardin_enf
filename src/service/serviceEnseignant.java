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

import Iservice.iIserviceEnseignant;
import entites.enseignant;
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
import javafx.scene.control.TableColumn;
import javax.swing.text.html.parser.DTDConstants;
import utils.DataBase;

/**
 *
 * @author souhaib
 */
public   class serviceEnseignant implements iIserviceEnseignant<enseignant>{
    
private Connection con;
    private Statement ste;

    public serviceEnseignant() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouterEnseignant(enseignant t) throws SQLException {
     ste = con.createStatement();
        String requeteInsert = "INSERT INTO `jardin`.`enseignant` (`id`, `cin`, `cv`, `email`,`password`, `diplomes`,`contrats_id`, `emplois_id`) VALUES ( '" + t.getId()+  "', '" + t.getCin()+ "', '" + t.getCv()+  "', '" + t.getEmail()+  "', '" + t.getPassword()+  "', '" + t.getDiplomes()+  "', '" + t.getContrats_id()+ "', '" + t.getEmplois_id()+  "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public void deleteEnseignant(enseignant t) throws SQLException {
      String querry = "DELETE FROM enseignant WHERE id =?";
        PreparedStatement d = con.prepareStatement(querry);
        d.setInt(1, t.getId());
        d.executeUpdate();
        System.out.println("ENSEIGNANT SUPPRIMER");
    }

    public void updateEnseignant(int id,int cin, String cv,String email,String password,String diplomes,int contrats_id,int emplois_id) throws SQLException {
      PreparedStatement pla = con.prepareStatement("update enseignant set   cin=?,cv=?,email=?,password=?,diplomes=?,contrats_id=?,emplois_id=? where id=?");
     
      
       pla.setInt(1, cin); 
          pla.setString(2, cv); 
          pla.setString(3, email); 
          pla.setString(4, password); 
          pla.setString(5, diplomes); 
          pla.setInt(6,contrats_id);
          pla.setInt(7, emplois_id);
          pla.setInt(8, id);
        pla.executeUpdate();

    }

    
  
    @Override
    public List<enseignant> afficherEnseignant()  {
   
           List<enseignant> ls =new ArrayList<enseignant>();
            try{
           PreparedStatement pt = con.prepareStatement("select * from enseignant");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                enseignant p = new enseignant(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7), rs.getInt(8));
                ls.add(p);
            }
            
            for (enseignant p : ls){
                System.out.println(p.toString());
            }
    }
    catch (SQLException ex) {
          System.out.println(ex.getMessage());
      } 
                return   ls; 
    }
     
  

    @Override
    public void updateEnseignant(enseignant t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ajouterEnseignant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public List<String> afficherIdcontrat() throws SQLException  {
     List<String> ls =new ArrayList<String>();
            PreparedStatement pt = con.prepareStatement("select id from contrat");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                ls.add(rs.getString("id"));
            }
            
    return ls; 
    }   
     public List<String> afficherIdEmploi() throws SQLException  {
     List<String> ls =new ArrayList<String>();
            PreparedStatement pt = con.prepareStatement("select id from emploi");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                ls.add(rs.getString("id"));
            }
            
    return ls; 
    } 
     public enseignant getenseignantById(int id) throws SQLException {
        String querry = "SELECT * FROM `enseignant` WHERE `id` = " + id + "";
        ste = con.createStatement();
        ResultSet rst = ste.executeQuery(querry);
       enseignant a = new enseignant();
        if (rst.next()) {
             a.setCin(rst.getInt("cin"));
          a.setCv(rst.getString("cv"));
          a.setEmail(rst.getString("email"));
           a.setPassword(rst.getString("password"));
            a.setDiplomes(rst.getString("diplomes"));
             a.setContrats_id(rst.getInt("contrats_id"));
              a.setEmplois_id(rst.getInt("emplois_id"));
           
        

          a.setId(id);
          System.out.println(a.toString());
          
        }else{System.out.println(" enseignant n'existe pas ");}
        return a; 
    }

    public void updateEnseignant(int l, int i, TableColumn<enseignant, String> cv, TableColumn<enseignant, String> email, TableColumn<enseignant, String> password, TableColumn<enseignant, String> diplomes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}

   