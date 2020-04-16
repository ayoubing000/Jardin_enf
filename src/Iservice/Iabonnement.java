/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Abonnement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ayoub
 */
public interface Iabonnement {
    
    public void creerAbonnement(Abonnement Abonnement)  throws SQLException ;
    public void modifierAbonnement(Abonnement Abonnement) throws SQLException ;
    public void supprimerAbonnement(Abonnement Abonnement) throws SQLException ;
    public List<Abonnement> afficherAbonnementAd() throws SQLException ;
    public void RechercherAbonnementByNom(String nom_abonnement) throws SQLException ;

}
