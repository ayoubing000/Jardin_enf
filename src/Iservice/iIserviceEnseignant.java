/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entites.enseignant;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author souhaib
 */
public interface iIserviceEnseignant <T>{
     void ajouterEnseignant(T t) throws SQLException;
    void deleteEnseignant(T t) throws SQLException;
    void updateEnseignant(T t) throws SQLException;
         public enseignant getenseignantById (int id) throws SQLException ;

    List<T> afficherEnseignant() throws SQLException;
    
}
