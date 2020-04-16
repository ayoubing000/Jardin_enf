/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Evenement;
import entites.InscriptionEvenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utlis.MaConnection;
import IServices.ServiceInscriptionEvenement;
import entites.user;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ons
 */
public class ServiceInscriptionEvenementImpl implements ServiceInscriptionEvenement {

    MaConnection maC = MaConnection.getInstance();
    Connection cnx = maC.getConnection();

    Evenement e;
    Statement ste;

    Logger logger = Logger.getLogger(ServiceInscriptionEvenementImpl.class.getName());

    public void participer(InscriptionEvenement p) throws SQLException {
        String req = "INSERT INTO inscription_evenement(evenement_id) VALUES(?)";
        String req0 = "SELECT nombreDePlace FROM evenement WHERE id=?";
        String req1 = "UPDATE evenement SET nombreDePlace=? WHERE id=?";

        PreparedStatement pste = cnx.prepareStatement(req);

        pste.setInt(1, p.getEvenement().getId());

        pste.executeUpdate();

        PreparedStatement pste0 = cnx.prepareStatement(req0);
        pste0.setInt(1, p.getEvenement().getId());
        ResultSet r0 = pste0.executeQuery();
        r0.next();
        int a = r0.getInt("nbr_participants");
        int b = a + 1;

        PreparedStatement pste1 = cnx.prepareStatement(req1);
        pste1.setInt(1, b);
        pste1.setInt(2, p.getEvenement().getId());
        pste1.executeUpdate();
    }

    public void annulerparticiper(InscriptionEvenement p) throws SQLException {
        String req = "INSERT INTO inscription_evenement(evenement_id) VALUES(?)";
        String req0 = "SELECT nombreDePlace FROM evenement WHERE id=?";
        String req1 = "UPDATE evenement SET nombreDePlace=? WHERE id=?";

        PreparedStatement pste = cnx.prepareStatement(req);
        pste.setInt(1, p.getEvenement().getId());

        pste.executeUpdate();

        PreparedStatement pste0 = cnx.prepareStatement(req0);
        pste0.setInt(1, p.getEvenement().getId());
        ResultSet r0 = pste0.executeQuery();
        r0.next();
        int a = r0.getInt("nbr_participants");
        int b = a - 1;

        PreparedStatement pste1 = cnx.prepareStatement(req1);
        pste1.setInt(1, b);
        pste1.setInt(2, p.getEvenement().getId());
        pste1.executeUpdate();
    }

    public List<InscriptionEvenement> findAll() {
        List<InscriptionEvenement> inscriptions= new ArrayList<>();
        InscriptionEvenement pa = null;
        Evenement e;
        try {
            String query = "SELECT * from inscription_evenement where evenement_id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, pa.getId());
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                inscriptions.add(new InscriptionEvenement(
                        res.getInt("id"),
                        new Evenement(res.getInt("evenement_id")),
                        new user(res.getInt("user_id")),
                        res.getDate("date_creation")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inscriptions;
    }

    @Override
    public List<InscriptionEvenement> findInscriptionsByEvenementId(int idEvenement) {
        List<InscriptionEvenement> inscriptions = new ArrayList<>();
        try {
            String query = "SELECT * from inscription_evenement where evenement_id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, idEvenement);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                inscriptions.add(new InscriptionEvenement(
                        res.getInt("id"),
                        new Evenement(res.getInt("evenement_id")),
                        new user(res.getInt("user_id")),
                        res.getDate("date_creation")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inscriptions;
    }

    @Override
    public void saveInscription(InscriptionEvenement inscription) {
        try {
            String query = "INSERT INTO inscription_evenement (evenement_id, user_id, date_creation) values (?,?,?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, inscription.getEvenement().getId());
            statement.setInt(2, inscription.getUser().getId());
            statement.setDate(3, inscription.getDateCreation());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void delete(InscriptionEvenement inscription) {
        try {
            String query = "DELETE  FROM inscription_evenement WHERE id=?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, inscription.getId());
            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
