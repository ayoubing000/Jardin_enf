/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import entites.Evenement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import utlis.MaConnection;
import IServices.ServiceEvenement;

/**
 *
 * @author ons
 */
public class ServiceEvenementImpl implements ServiceEvenement {
    
    Connection connection = MaConnection
            .getInstance()
            .getConnection();
    Statement ste;
    
    private static ServiceEvenementImpl instance;
    
    public static ServiceEvenementImpl getInstance() {
        if (instance == null) {
            instance = new ServiceEvenementImpl();
        }
        return instance;
    }
    
    public ServiceEvenementImpl() {
        try {
            ste = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public void AjoutEvent(Evenement t) {
        try {
            ste = connection.createStatement();
            String requeteInsert = "INSERT INTO `jardin`.`evenement` (`titre`, `image`, `date_debut`, `date_fin`, `type`,`description`,`active`, `nombreDePlace`) VALUES ( '"
                    + t.getTitre() + " ','" + t.getImage() + "', '" + t.getDate_debut() + "','" + t.getDate_fin() + "','" + t.getType() + "', '" + t.getDescription()
                    + "','" + (t.isActive() ? 1 : 0) + "', '" + t.getNombreDePlace() + "');";
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void modifierEvenement(Evenement e, int id) {
        
        String requete = "UPDATE evenement SET titre=?,image=?,date_debut=?,date_fin=?,type=?,description=?,active=?,nombreDePlace=? WHERE id=?";
        PreparedStatement p;
        try {
            p = connection.prepareStatement(requete);
            
            p.setString(1, e.getTitre());
            p.setString(2, e.getImage());
            p.setDate(3, e.getDate_debut());
            p.setDate(4, e.getDate_fin());
            p.setString(5, e.getType());
            p.setString(6, e.getDescription());
            p.setBoolean(7, e.isActive());
            p.setInt(8, e.getNombreDePlace());
            p.setInt(9, e.getId());
            
            p.executeUpdate();
            int rowsUpdated = p.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("L'evenement a été modifier avec succès");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }
        
    }
    
    public void modifyEvent(Evenement e, int id) {
        try {
            String requete = "UPDATE evenement SET titre=?,image=?,date_debut=?,date_fin=?,type=?,description=?,active=?,nombreDePlace=? WHERE id=?";
            PreparedStatement p = connection.prepareStatement(requete);
            p.setInt(1, e.getId());
            p.setString(2, e.getTitre());
            p.setString(3, e.getImage());
            p.setDate(4, e.getDate_debut());
            p.setDate(5, e.getDate_fin());
            p.setString(6, e.getType());
            p.setString(7, e.getDescription());
            p.setBoolean(8, e.isActive());
            p.setInt(9, e.getNombreDePlace());
            System.out.println("MODIFIé C'est BON");
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
        
    }
    
    
    
    @Override
    public List<Evenement> findAllwithInscriptionsCount() {
        List<Evenement> result = new ArrayList<>();
        try {
            String query = "select e.*, count(e.id) as nb_inscriptions from evenement e "
                    + "left join inscription_evenement ie on ie.evenement_id = e.id "
                    + "group by e.id ";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Evenement e = new Evenement(rs.getInt("id"), rs.getString("titre"), rs.getString("image"), rs.getDate("date_debut"),
                        rs.getDate("date_fin"), rs.getString("type"), rs.getString("description"), rs.getBoolean("active"),
                        rs.getInt("nombreDePlace"));
                e.setNbrInscriptions(rs.getInt("nb_inscriptions"));
                result.add(e);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public List<Evenement> findAll() {
        ArrayList<Evenement> eventList = new ArrayList<>();
        
        try {
            String sql = "Select * FROM Evenement";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                eventList.add(new Evenement(rs.getInt("id"), rs.getString("titre"), rs.getString("image"), rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getString("type"), rs.getString("description"), rs.getBoolean("active"), rs.getInt("nombreDePlace")));
            }
            
        } catch (SQLException ex) {
            
            System.out.println("Erreur d'affichage" + ex.getMessage());
        }
        return eventList;
        
    }
    
    @Override
    public Evenement rechercheEventParNom(String nom) {
        
        try {
            Statement ps = connection.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from club where titre like '%" + nom + "%' ");
            while (res.next()) {
                int id = res.getInt("id");
                String titre = res.getString("titre");
                String image = res.getString("image");
                Date dateDebut = res.getDate("dateDebut");
                Date dateFin = res.getDate("dateFin");
                String type = res.getString("type");
                String description = res.getString("description");
                Boolean active = res.getBoolean("active");
                int nombreDePlace = res.getInt("nombreDePlace");
                return new Evenement(id, titre, image, dateDebut, dateFin, type, description, active, nombreDePlace);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        System.out.println("evenement non trouvé ");
        return null;
    }
    
    public int nbrInscription(int id) throws SQLException {
        Statement stm = connection.createStatement();
        String req2 = "select count(*) FROM inscription_evenement WHERE id=" + id + "";
        ResultSet res = stm.executeQuery(req2);
        
        return res.getInt(1);
    }
    
    public ArrayList<Evenement> getnbrr() throws SQLException {
        ArrayList<Evenement> retour = new ArrayList<Evenement>();
        
        Statement stm = connection.createStatement();
        Evenement per = null;
        String req = "SELECT id FROM `evenement` ";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            int ide = resultat.getInt("id");
            
            per = new Evenement(ide, nbrInscription(ide));
            
            retour.add(per);
            
        }
        
        return retour;
    }

    /*public ArrayList<evenement> getnbrr1() throws SQLException{

        ArrayList<evenement> retour = new ArrayList<evenement>();
            PreparedStatement stmt = c.prepareStatement("SELECT id FROM `evenement` ");
            
            int a =0;
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) <= 0) {
                    stmt = c.prepareStatement("select count(*) FROM inscription_evenement WHERE id="+rs.getInt(1)+"");

                   
         
                    stmt.execute();
                    
                    System.out.println("Insertion faite!");
                } else {
                    System.out.println(" existe déja ");
                }
            }
            rs.close();
            stmt.close();
       return retour;
    }
     */
    @Override
    public XYChart.Series<String, Integer> topEvenement() {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            
            String req = "SELECT titre,nombreDePlace  FROM evenement ";
            Statement ste = connection.createStatement();
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                series.getData().add(new XYChart.Data<>(res.getString(1), res.getInt(2)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return series;
    }
    
    
    
    @Override
    public void updateEvent(Evenement e) {
        try {
            String query = "UPDATE evenement "
                    + "SET titre=?,"
                     + "image=?,"
                    + "date_debut=?,"
                    + "date_fin=?,"
                    + "type=?,"
                    + "description=?,"
                    + "active=?,"
                    + "nombreDePlace=? "
                    + "WHERE id=?";
          
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, e.getTitre());
            statement.setString(2, e.getImage());
            statement.setDate(3, e.getDate_debut());
            statement.setDate(4, e.getDate_fin());
            statement.setString(5, e.getType());
            statement.setString(6, e.getDescription());
            statement.setBoolean(7, e.isActive());
            statement.setInt(8, e.getNombreDePlace());
            statement.setInt(9, e.getId());

            int updatedRows = statement.executeUpdate();
            System.out.println(statement.toString());
            System.out.println(updatedRows);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  

    @Override
    public void delete(int id) {
 
    try {
            PreparedStatement pt = connection.prepareStatement(" DELETE FROM evenement WHERE id=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }

   

   
    
}
