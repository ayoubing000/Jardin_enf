/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author ayoub
 */
public class Abonnement {
    private int id;
    private int enf_id;
    private Date data_debut;
    private Date date_fin;
    private String type;
    private String Description;
    private String statu="no valid";
    private String statu_paiment ="en attend";
    private float total;

    public Abonnement() {
    }
    public Abonnement(int id, int enf_id, Date data_debut, Date date_fin, String type, String Description, String statu, String statu_paiment, float total) {
        this.id = id;
        this.enf_id = enf_id;
        this.data_debut = data_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.Description = Description;
        this.statu = statu;
        this.statu_paiment = statu_paiment;
        this.total = total;

    }
    public Abonnement(int id, int enf_id, Date data_debut, Date date_fin, String type, String Description, String statu, String statu_paiment) {
        this.id = id;
        this.enf_id = enf_id;
        this.data_debut = data_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.Description = Description;
        this.statu = statu;
        this.statu_paiment = statu_paiment;
    }

    public Abonnement(int id, int enf_id, Date data_debut, Date date_fin, String type, String Description, float total) {
        this.id = id;
        this.enf_id = enf_id;
        this.data_debut = data_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.Description = Description;
        this.total = total;
    }

    public Abonnement(int id, int enf_id, Date data_debut, String type, String Description, float total) {
        this.id = id;
        this.enf_id = enf_id;
        this.data_debut = data_debut;
        this.type = type;
        this.Description = Description;
        this.total = total;
    }
    

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

   

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnf_id() {
        return enf_id;
    }

    public void setEnf_id(int enf_id) {
        this.enf_id = enf_id;
    }

    public Date getData_debut() {
        return data_debut;
    }

    public void setData_debut(Date data_debut) {
        this.data_debut = data_debut;
    }

    public Date getDate_fin() {
        return date_fin;
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
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getStatu_paiment() {
        return statu_paiment;
    }

    public void setStatu_paiment(String statu_paiment) {
        this.statu_paiment = statu_paiment;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", enf_id=" + enf_id + ", data_debut=" + data_debut + ", date_fin=" + date_fin + ", type=" + type + ", Description=" + Description + ", statu=" + statu + ", statu_paiment=" + statu_paiment + '}';
    }
    
}
