/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entites.Evenement;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author ons
 */
public interface ServiceEvenement {

    public void modifierEvenement(Evenement e, int id);

  

    public List<Evenement> findAll();

    public Evenement rechercheEventParNom(String nom);

    public XYChart.Series<String, Integer> topEvenement();

    public void delete(int id);

    public void AjoutEvent(Evenement evenenement);

    public void updateEvent(Evenement evenenement);

    public List<Evenement> findAllwithInscriptionsCount();
}
