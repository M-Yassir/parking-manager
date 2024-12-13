package com.example.demo5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LOGIN extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

            // Configurer la scène
            Scene scene = new Scene(root);

            // Ajouter une feuille de style optionnelle
            // scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            // Configurer la fenêtre principale
            primaryStage.setTitle("Parking Manager");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

