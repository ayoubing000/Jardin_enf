/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author ayoub
 */
public class Enfant {
   
private int matricul_abn;
private int matricul_prt;
private int id_repa;
private String nom;
private String prenom;
private Date date_naissaince;
private int age;

    public Enfant(String nom) {
        this.nom = nom;
    }

    public Enfant() {
    }

    public int getMatricul_abn() {
        return matricul_abn;
    }

    public int getMatricul_prt() {
        return matricul_prt;
    }

    public int getId_repa() {
        return id_repa;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_naissaince() {
        return date_naissaince;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Enfant{" + "matricul_abn=" + matricul_abn + ", matricul_prt=" + matricul_prt + ", id_repa=" + id_repa + ", nom=" + nom + ", prenom=" + prenom + ", date_naissaince=" + date_naissaince + ", age=" + age + '}';
    }

   

}
