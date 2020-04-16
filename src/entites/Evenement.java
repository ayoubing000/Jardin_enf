/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ons
 */
public class Evenement {

    private int id;
    private String titre;
    private String image;
    private Date date_debut;
    private Date date_fin;
    private String type;
    private String description;
    private boolean active;
    private InscriptionEvenement inscriptions;

    private int nombreDePlace;
    private int nbrInscriptions;

    public Evenement(int id, String titre, String image, Date date_debut, Date date_fin, String type, String description, boolean active, int nombreDePlace) {
        this.id = id;
        this.titre = titre;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.description = description;
        this.nombreDePlace = nombreDePlace;
        this.active = active;
    }

    public Evenement(String titre, String image, Date date_debut, Date date_fin, String type, String description, boolean active, int nombreDePlace) {
        this.titre = titre;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.description = description;
        this.active = active;
        this.nombreDePlace = nombreDePlace;
    }

    public Evenement(String titre, String image, String type, String description, int nombreDePlace) {
        this.titre = titre;
        this.image = image;
        this.type = type;
        this.description = description;
        this.nombreDePlace = nombreDePlace;
    }

    public Evenement(String image) {
        this.image = image;
    }
    

    public Evenement() {
    }

    public Evenement(int id, int nombreDePlace) {
        this.id = id;
        this.nombreDePlace = nombreDePlace;
    }

    public Evenement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InscriptionEvenement getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(InscriptionEvenement inscriptions) {
        this.inscriptions = inscriptions;
    }

    public String getTitre() {
        return titre;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombreDePlace() {
        return nombreDePlace;
    }

    public void setNombreDePlace(int nombreDePlace) {
        this.nombreDePlace = nombreDePlace;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.titre);
        hash = 29 * hash + Objects.hashCode(this.type);
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
        final Evenement other = (Evenement) obj;
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", titre=" + titre + ", image=" + image + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", type=" + type + ", description=" + description + ", active=" + active + ", nombreDePlace=" + nombreDePlace + '}';
    }

    public void setJour(Date date1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String valid() {
        if (titre.equals("")) {
            return "titre";
        }
        if (description.equals("")) {
            return "description";
        }
        if (image.equals("")) {
            return "image";
        }

        return null;
    }

    public int getNbrInscriptions() {
        return nbrInscriptions;
    }

    public void setNbrInscriptions(int nbrInscriptions) {
        this.nbrInscriptions = nbrInscriptions;
    }

    
}
