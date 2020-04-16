/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entites.emploi;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author souhaib
 */
 
public interface Iservice<T> {
    void ajouterEmploi(T t) throws SQLException;
    void deleteEmploi(T t) throws SQLException;
    void updateEmploi(T t) throws SQLException;
     public emploi getsalleById(int id) throws SQLException;
    List<T> afficherEmploi() throws SQLException;
}

