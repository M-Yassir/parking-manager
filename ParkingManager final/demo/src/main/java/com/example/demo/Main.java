package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("style1.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        Controller controller = loader.getController();

        // Pass the Stage reference to the controller
        controller.setStage(stage);

        // Scene operations
        Scene scene = new Scene(root);

        // Stage operations
        Image logo = new Image("/images/logo.png");
        stage.getIcons().add(logo);
        stage.setTitle("Parking Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}