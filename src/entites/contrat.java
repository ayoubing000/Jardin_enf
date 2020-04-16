/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;


import java.util.Date;
import java.util.Objects;

/**
 *
 * @author souhaib
 */
public class contrat {

   
     private int id;
     private Date date_signature;
     private Date date_debut;
     private Date date_fin;
     private Date date_resiliation;
     private String type;
     private int enseignant_id;

    public contrat(int id, String type,Date date_signature, Date date_debut, Date date_fin, Date date_resiliation,int enseignant_id) {
        this.id = id;
        this.date_signature = date_signature;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.date_resiliation = date_resiliation;
        this.type = type;
        this.enseignant_id=enseignant_id ;
    }

    public contrat() {
    }

    public int getEnseignant_id() {
        return enseignant_id;
    }

    public void setEnseignant_id(int enseignant_id) {
        this.enseignant_id = enseignant_id;
    }

    

  

    public Date getDate_signature() {
        return date_signature;
    }

    public void setDate_signature(Date date_signature) {
        this.date_signature = date_signature;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Date getDate_resiliation() {
        return date_resiliation;
    }

    public void setDate_resiliation(Date date_resiliation) {
        this.date_resiliation = date_resiliation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "contrat{" + "id=" + id + ", date_signature=" + date_signature + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", date_resiliation=" + date_resiliation + ", type=" + type + ", enseignant_id=" + enseignant_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.date_signature);
        hash = 89 * hash + Objects.hashCode(this.date_debut);
        hash = 89 * hash + Objects.hashCode(this.date_fin);
        hash = 89 * hash + Objects.hashCode(this.date_resiliation);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + this.enseignant_id;
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
        final contrat other = (contrat) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.enseignant_id != other.enseignant_id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.date_signature, other.date_signature)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        if (!Objects.equals(this.date_resiliation, other.date_resiliation)) {
            return false;
        }
        return true;
    }

   
  
   





}