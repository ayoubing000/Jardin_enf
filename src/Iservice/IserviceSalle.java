/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entites.salle;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author souhaib
 */
public interface IserviceSalle <T>{
     void ajouterSalle(T t) throws SQLException;
    void deleteSalle(T t) throws SQLException;
    void updateSalle(T t) throws SQLException;
    public salle getsalleById(int id) throws SQLException;
    List<T> afficherSalle() throws SQLException;
    
}
