/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entites.InscriptionEvenement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ons
 */
public interface ServiceInscriptionEvenement {

    public void participer(InscriptionEvenement p) throws SQLException;

    public void annulerparticiper(InscriptionEvenement p) throws SQLException;

    public List<InscriptionEvenement> findAll();
    
    public List<InscriptionEvenement> findInscriptionsByEvenementId(int idEvenement);

    public void saveInscription(InscriptionEvenement inscription);

    public void delete(InscriptionEvenement inscription);


}
