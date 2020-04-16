/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Desktop;
import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ons
 */
public class JardinFXMain extends Application {

    public static Desktop desktop = Desktop.getDesktop();
    public static FileChooser fileChooser = new FileChooser();
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("File Chooser Sample");
        JardinFXMain.stage = stage;

       // Parent root = FXMLLoader.load(getClass().getResource("ConsulterEventParent.fxml"));
        
     Parent root = FXMLLoader.load(getClass().getResource("AjoutEvenement.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
