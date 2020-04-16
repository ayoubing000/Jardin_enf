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
public class InscriptionEvenement {

    private int id;
    private Evenement evenement;
    private user user;
    private Date dateCreation;

    public InscriptionEvenement() {
    }

    public InscriptionEvenement(int id, Evenement evenement, user user, Date dateCreation) {
        this.id = id;
        this.user = user;
        this.evenement = evenement;
        this.dateCreation = dateCreation;
    }

    public InscriptionEvenement(int id) {
        this.id = id;
    }

    public InscriptionEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final InscriptionEvenement other = (InscriptionEvenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    
    @Override
    public String toString() {
        return "inscription_evenement{" + "id=" + id + ", date_creation=" + dateCreation + '}';
    }

}
