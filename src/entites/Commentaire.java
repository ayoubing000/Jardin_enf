/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

;

import java.sql.Date;

/**
 *
 * @author ons
 */


public class Commentaire {

    private int id;
    private Evenement evenement;
    private user user;
    private String contenu;
    private Date creation_date;

    public Commentaire(int id, int evenement_id, int user_id, String contenu, Date creation_date) {
        this.id = id;
        this.evenement = new Evenement(evenement_id);
        this.user = new user(user_id);
        this.contenu = contenu;
        this.creation_date = creation_date;
    }

    public Commentaire(int id, int evenement_id, int user_id, String contenu, Date creation_date, String username) {
        this.id = id;
        this.evenement = new Evenement(evenement_id);
        this.user = new user(user_id);
        this.user.setUserName(username);
        this.contenu = contenu;
        this.creation_date = creation_date;
    }

    public Commentaire(String contenu) {
        this.contenu = contenu;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public user getUser() {
        return user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
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
        final Commentaire other = (Commentaire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id + ", contenu=" + contenu + ", creation_date=" + creation_date + '}';
    }

}
