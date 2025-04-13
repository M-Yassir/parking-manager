package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class LOGIN extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Set the stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Parking Manager");
            primaryStage.getIcons().add(new Image("/images/logo.png"));

            // Add the close request handler
            primaryStage.setOnCloseRequest(this::handleCloseRequest);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCloseRequest(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to quit?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            event.consume();  // Cancel the close request
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

