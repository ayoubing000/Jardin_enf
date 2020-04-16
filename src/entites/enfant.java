/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author ons
 */
public class enfant {
    private int id;
    private int matricul_abn;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String image;
    private int age;
    private int id_parent ;

    public enfant(int id, int matricul_abn, String nom, String prenom, Date date_naissance, String image, int age, int id_parent) {
        this.id = id;
        this.matricul_abn = matricul_abn;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.image = image;
        this.age = age;
        this.id_parent = id_parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricul_abn() {
        return matricul_abn;
    }

    public void setMatricul_abn(int matricul_abn) {
        this.matricul_abn = matricul_abn;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_parent() {
        return id_parent;
    }

    public void setId_parent(int id_parent) {
        this.id_parent = id_parent;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final enfant other = (enfant) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enfant{" + "id=" + id + ", matricul_abn=" + matricul_abn + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", image=" + image + ", age=" + age + ", id_parent=" + id_parent + '}';
    }
    
    
    
    
}
