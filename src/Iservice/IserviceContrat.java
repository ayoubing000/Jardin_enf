/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entites.contrat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author souhaib
 */
public interface IserviceContrat<T> {
     void ajouterContrat(T t) throws SQLException;
    void deleteContrat(T t) throws SQLException;
    void updateContrat(T t) throws SQLException;
public contrat getContratByType(String type) throws SQLException ;

     public contrat getcontratById (int id) throws SQLException ;

    List<T> affichierContrat() throws SQLException;
}
