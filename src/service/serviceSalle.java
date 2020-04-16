/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Iservice.IserviceSalle;
import entites.salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataBase;

/**
 *
 * @author souhaib
 */
public class serviceSalle implements IserviceSalle<salle>{
        
private Connection con;
    private Statement ste;

    public serviceSalle() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouterSalle(salle t) throws SQLException {
     ste = con.createStatement();
        String requeteInsert = "INSERT INTO `jardin`.`salle` (`id`, `libelle`, `emploi_id`) VALUES ( '" + t.getId()+  "', '" + t.getLibelle()+ "', '" + t.getEmploi_id()+ "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public void deleteSalle(salle t) throws SQLException {
      String querry = "DELETE FROM salle WHERE id =?";
        PreparedStatement d = con.prepareStatement(querry);
        d.setInt(1, t.getId());
        d.executeUpdate();
        System.out.println("salle supprimer");
    }
    public void updateSalle(int id , String libelle , int emploi_id) throws SQLException {
      PreparedStatement pla = con.prepareStatement("update salle set   libelle=?,emploi_id=? where id=?");
                pla.setString(1, libelle);
             pla.setInt(2,emploi_id );   
        pla.setInt(3, id);
    
        pla.executeUpdate();
 System.out.println("salle modifier");
    }

@Override
     public salle getsalleById(int id ) throws SQLException {
        String querry = "SELECT * FROM `salle` WHERE `id` = " + id + "";
        ste = con.createStatement();
        ResultSet rst = ste.executeQuery(querry);
       salle a = new salle();
        if (rst.next()) {
          a.setLibelle(rst.getString("libelle"));
          a.setEmploi_id(rst.getInt("emploi_id"));

          a.setId(id);
          return a; 
          
        }
        else{System.out.println(" salle n'existe pas ");
        }
        return a; 
       
    }    
     
  
  
    @Override
    public List<salle> afficherSalle() {
   
  List<salle> ls =new ArrayList<salle>();
  try{
            PreparedStatement pt = con.prepareStatement("select * from salle");
        
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                
                salle p = new salle(rs.getInt(1),rs.getString(2),rs.getInt(3));
                ls.add(p);
            }
            
            for (salle p : ls){
                System.out.println(p.toString());
            
     }
            
     
    }
    catch (SQLException ex) {
          System.out.println(ex.getMessage());
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

    @Override
    public void updateSalle(salle t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    

   

   

   


   

   

    


   

   
}
 

