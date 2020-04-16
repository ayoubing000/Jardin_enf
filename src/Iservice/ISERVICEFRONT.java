/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author souhaib
 */
public interface ISERVICEFRONT<EM,C,E> {
    List<EM> afficherEmploiFront() throws SQLException;
    List<C> affichierContratFront() throws SQLException;
        List<E> afficherEnseignantFront() throws SQLException;

}
