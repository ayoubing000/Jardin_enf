/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entites.Commentaire;
import entites.Evenement;
import java.util.List;

/**
 *
 * @author ons
 */
public interface ServiceCommentaire {
    
    public List<Commentaire> findAll();
    public List<Commentaire> findByEvenement(Evenement e);

    public void save(Commentaire comment);
    
}
