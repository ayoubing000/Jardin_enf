/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.AbonnementAd;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ayoub
 */
public interface IabonnementAd {
    public void creerAbonnementAd(AbonnementAd AbonnementAd) throws SQLException;
    public void modifierAbonnementAd(AbonnementAd AbonnementAd) throws SQLException;
    public void supprimerAbonnementAd(AbonnementAd AbonnementAd) throws SQLException;
    public List<AbonnementAd> afficherAbonnementAd() throws SQLException;
    public void RechercherAbonnementAdId(int id_abonnement) throws SQLException;

}
