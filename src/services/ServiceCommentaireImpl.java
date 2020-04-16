/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IServices.ServiceCommentaire;
import entites.Commentaire;
import entites.Evenement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utlis.MaConnection;

/**
 *
 * @author ons
 */
public class ServiceCommentaireImpl implements ServiceCommentaire {

    Connection connection = MaConnection
            .getInstance()
            .getConnection();

    public void modifierCommentaire(Commentaire c, int id) {
        String sql = "UPDATE commentaire SET `id`=?,`evenement_id`=?,`user_id`=?,`contenu`=?,`creation_date`=? WHERE id=" + c.getId();
        PreparedStatement ste;
        try {
            ste = connection.prepareStatement(sql);
            ste.setInt(1, c.getId());
            ste.setInt(2, c.getEvenement().getId());
            ste.setInt(3, c.getUser().getId());
            ste.setString(4, c.getContenu());
            ste.setDate(5, c.getCreation_date());

            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de produit" + c.getId() + " a été éffectué avec succée ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void supprimerCommentaire(Commentaire c) {

        try {
            String req = "DELETE FROM `commentaire` WHERE `commentaire`.`id` = ?";
            PreparedStatement ste = connection.prepareStatement(req);
            ste.setInt(1, c.getId());
            ste.executeUpdate();
            System.out.println("element supprimer");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ajouterCommentaire(Commentaire c) throws SQLException {
        Statement ste = this.connection.createStatement();
        String req1 = "INSERT INTO `commentaire` (`id`, `evenement_id`, 'user_id',`contenu`,`creation_date`) VALUES ('" + c.getId() + "', '" + c.getEvenement().getId() + "', '" + c.getUser().getId() + "', '" + c.getContenu() + "', '" + c.getCreation_date() + "');";
        ste.executeUpdate(req1);
    }

    @Override
    public List<Commentaire> findAll() {
        List<Commentaire> result = new ArrayList<>();
        try {
            String sql = "select * from commentaire";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(new Commentaire(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Commentaire> findByEvenement(Evenement e) {
        List<Commentaire> result = new ArrayList<>();
        try {
            String query = "select c.*, u.username as username from commentaire c join user u on u.id=c.user_id WHERE evenement_id = ? ;";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, e.getId());
            ResultSet res = st.executeQuery();
            while (res.next()) {
                result.add(new Commentaire(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getDate(5), res.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void save(Commentaire c) {
        try {
            Statement ste = this.connection.createStatement();
            String query = "INSERT INTO `commentaire` (`id`, `evenement_id`, user_id,`contenu`,`creation_date`) VALUES ('"
                    + c.getId() + "', '" + c.getEvenement().getId() + "', '" + c.getUser().getId() + "', '" + c.getContenu() + "', '" + c.getCreation_date() + "');";
            ste.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
