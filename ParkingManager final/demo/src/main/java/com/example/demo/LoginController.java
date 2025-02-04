package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            // Charger la page d'accueil
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("style1.fxml"));
                Parent root = loader.load();

                // Obtenir la scène actuelle
                Stage stage = (Stage) usernameField.getScene().getWindow();

                // Remplacer la scène par celle de la page d'accueil
                stage.setScene(new Scene(root));
                stage.setTitle("Home - Parking Manager");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page d'accueil.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password. Please try again.");
        }
    }

    @FXML
    private void handleExit() {
        // Fermer l'application
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    private boolean authenticate(String username, String password) {
        // Remplacez par une logique réelle
        return "admin".equals(username) && "1234".equals(password);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}