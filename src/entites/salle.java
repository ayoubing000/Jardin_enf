/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author souhaib
 */
public class salle {
    
    private int id ;
    private String libelle ;
    private int emploi_id;


    public salle(int id, String libelle,int emploi_id) {
        this.id = id;
        this.libelle = libelle;
        this.emploi_id=emploi_id;
    }

    public salle() {
    }

    public salle(int id) {
        this.id = id;
    }

    public salle(String libelle, int emploi_id) {
        this.libelle = libelle;
        this.emploi_id = emploi_id;
    }

   

    public int getId() {
        return id;
    }

    public int getEmploi_id() {
        return emploi_id;
    }

    public void setEmploi_id(int emploi_id) {
        this.emploi_id = emploi_id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.libelle);
        hash = 97 * hash + this.emploi_id;
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
        final salle other = (salle) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.emploi_id != other.emploi_id) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "salle{" + "id=" + id + ", libelle=" + libelle + ", emploi_id=" + emploi_id + '}';
    }

   

    
    
}
