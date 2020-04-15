/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jardin;

import Entity.AbonnementAd;
import Service.Abonnement_adminService;
import Service.Abonnemnt_Service;
import Service.Parent_service;
import Service.Purchase_Service;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayoub
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
    /*  java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                 AbonnementAd a3= new AbonnementAd(15,"welcome",80f,"dzd");
               AbonnementAd a4= new AbonnementAd(5,"test2",20f,"test2");
               Abonnement_adminService  s = new  Abonnement_adminService ();
               //s.creerAbonnementAd(a3);
               s.afficherAbonnementAd();*/
        Parent_service p = new Parent_service();
        p.afficher_abn();
 }
    
}
