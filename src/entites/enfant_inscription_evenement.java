/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author ons
 */
public class enfant_inscription_evenement {
    private int enfant_id;
    private int inscription_evenement_id;

    public enfant_inscription_evenement(int enfant_id, int inscription_evenement_id) {
        this.enfant_id = enfant_id;
        this.inscription_evenement_id = inscription_evenement_id;
    }

    public int getEnfant_id() {
        return enfant_id;
    }

    public void setEnfant_id(int enfant_id) {
        this.enfant_id = enfant_id;
    }

    public int getInscription_evenement_id() {
        return inscription_evenement_id;
    }

    public void setInscription_evenement_id(int inscription_evenement_id) {
        this.inscription_evenement_id = inscription_evenement_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.enfant_id;
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
        final enfant_inscription_evenement other = (enfant_inscription_evenement) obj;
        if (this.enfant_id != other.enfant_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enfant_inscription_evenement{" + "enfant_id=" + enfant_id + ", inscription_evenement_id=" + inscription_evenement_id + '}';
    }
    
    
}
