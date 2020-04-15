/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ayoub
 */
public class AbonnementAd {
    private int id;
    private String Description;
    private float prix;
    private String type;

    public AbonnementAd() {
    }

    public AbonnementAd(int id, String Description, float prix, String type) {
        this.id = id;
        this.Description = Description;
        this.prix = prix;
        this.type = type;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
     @Override
    public String toString() {
        return "Abonnement Admin{" + "id =" + id + ", nom =" + Description + ", prix =" + prix + ", type =" + type + '}';
    }
      @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbonnementAd other = (AbonnementAd) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
