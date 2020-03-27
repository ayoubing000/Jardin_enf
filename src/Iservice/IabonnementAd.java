/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.AbonnementAd;
import java.util.List;

/**
 *
 * @author ayoub
 */
public interface IabonnementAd {
    public void creerAbonnementAd(AbonnementAd AbonnementAd);
    public void modifierAbonnementAd(AbonnementAd AbonnementAd);
    public void supprimerAbonnementAd(AbonnementAd AbonnementAd);
    public List<AbonnementAd> afficherAbonnementAd();
    public AbonnementAd RechercherAbonnementAdId(int id_abonnement);
    public AbonnementAd RechercherAbonnementAdByNom(String nom_abonnement);
    
}
