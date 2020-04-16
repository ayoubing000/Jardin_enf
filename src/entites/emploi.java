/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javafx.scene.control.ComboBox;

/**
 *
 * @author souhaib
 */
public class emploi {
     
     private int id;
     private Date jour ;
     private String description;
      private int salle_id; 
      private int enseignant_id;

    public emploi(int id, Date jour, String description ,int enseignant_id ,int salle_id) {
        this.id = id;
        this.jour = jour;
        this.description = description;
          this.enseignant_id= enseignant_id ;
        this.salle_id=salle_id;
      
        
    }

    public emploi(Date jour, String description) {
        this.jour = jour;
        this.description = description;
    }
    

    public emploi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnseignant_id() {
        return enseignant_id;
    }

    public void setEnseignant_id(int enseignant_id) {
        this.enseignant_id = enseignant_id;
    }

    public int getSalle_id() {
        return salle_id;
    }

    public void setSalle_id(int salle_id) {
        this.salle_id = salle_id;
    }

  
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.jour);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + this.salle_id;
        hash = 73 * hash + this.enseignant_id;
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
        final emploi other = (emploi) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.salle_id != other.salle_id) {
            return false;
        }
        if (this.enseignant_id != other.enseignant_id) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.jour, other.jour)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "emploi{" + "id=" + id + ", jour=" + jour + ", description=" + description + ", salle_id=" + salle_id + ", enseignant_id=" + enseignant_id + '}';
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    

   
    
   
   
    
    

 

    
}
