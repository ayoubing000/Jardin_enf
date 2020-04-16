/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Iservice.ISERVICEFRONT;
import entites.contrat;
import entites.emploi;
import entites.enseignant;
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
public class FRONTSERVICE implements ISERVICEFRONT <emploi,contrat,enseignant>{
    private Connection con;
    private Statement ste;


    public FRONTSERVICE () {
        con = DataBase.getInstance().getConnection();
        }
     
    public List<enseignant> afficherEnseignantFront()  {
   
           List<enseignant> ls =new ArrayList<enseignant>();
            try{
           PreparedStatement pt = con.prepareStatement("select * from enseignant WHERE id =6");
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
       public List<emploi> afficherEmploiFront()  {
   
  List<emploi> ls =new ArrayList<emploi>();
  try{
            PreparedStatement pt = con.prepareStatement("select * from emploi WHERE enseignant_id =6 ");
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
          public List<contrat> affichierContratFront()  {
   
  List<contrat> ls =new ArrayList<contrat>();
  try{
            PreparedStatement pt = con.prepareStatement("select * from contrat  WHERE enseignant_id=6 ");
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

   
       
       
       
       
}
