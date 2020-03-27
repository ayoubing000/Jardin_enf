/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import Entity.Abonnement;
import java.util.List;

/**
 *
 * @author ayoub
 */
public interface Iabonnement {
    
    public void creerAbonnement(Abonnement Abonnement);
    public void modifierAbonnement(Abonnement Abonnement);
    public void supprimerAbonnement(Abonnement Abonnement);
    public List<Abonnement> afficherAbonnementAd();
    public Abonnement RechercherAbonnementId(int id_abonnement);
    public Abonnement RechercherAbonnementByNom(String nom_abonnement);
    
}
