package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.*;



public class Controller {

    @FXML
    private AnchorPane scenepane;
    @FXML
    private TableView<CustomerVehiclePair> table;
    @FXML
    private TextField searchField; // Champ de texte pour la recherche
    @FXML
    private TableColumn<CustomerVehiclePair, String> RegistrationNumber;
    @FXML
    private TableColumn<CustomerVehiclePair, String> Name;
    @FXML
    private TableColumn<CustomerVehiclePair, String> Model;
    @FXML
    private TableColumn<CustomerVehiclePair, String> Type;
    @FXML
    private TableColumn<CustomerVehiclePair, Integer> Time;
    @FXML
    private TableColumn<CustomerVehiclePair, String> Subscription;
    @FXML
    private TableColumn<CustomerVehiclePair, Integer> Price;
    @FXML
    private TableColumn<CustomerVehiclePair, String> FullName;
    @FXML
    private TableColumn<CustomerVehiclePair, String> NID;

    private ObservableList<CustomerVehiclePair> data;
    // This is for the main buttons don't see that
    public void add(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addvehicle.fxml"));
            Parent root = loader.load();

            // Get the controller for the add vehicle window
            AddVehicleController addVehicleController = loader.getController();

            // Set the main controller reference
            addVehicleController.setMainController(this);

            Stage AddStage = new Stage();
            AddStage.setTitle("Add Vehicle");
            AddStage.setScene(new Scene(root));
            AddStage.show();
        } catch (IOException ex) {
            // Create an alert to show the error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to open Add Vehicle window");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();

            // Log the exception for debugging
            ex.printStackTrace();
        }
    }
    public void addNewVehicle(CustomerVehiclePair newVehicle) {
        data.add(newVehicle);
    }
    public void remove(ActionEvent e) {
        CustomerVehiclePair selectedPair = table.getSelectionModel().getSelectedItem();
        if (selectedPair != null) {
            // Supprimer l'élément sélectionné de la liste des données
            data.remove(selectedPair);
            table.refresh(); // Rafraîchir la table
        } else {
            // Afficher une alerte si aucun élément n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Aucun élément sélectionné");
            alert.setContentText("Veuillez sélectionner un élément à supprimer.");
            alert.showAndWait();
        }
    }
    public void modify(ActionEvent e) {
        CustomerVehiclePair selectedPair = table.getSelectionModel().getSelectedItem();
        if (selectedPair != null) {
            // Boîte de dialogue pour modifier les propriétés du véhicule
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Modifier le véhicule");
            dialog.setHeaderText("Modifier les informations du véhicule");

            // Ajouter des champs de texte pour chaque propriété
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            TextField nameField = new TextField(selectedPair.getVehicleName());
            TextField registrationField = new TextField(selectedPair.getVehicleRegistrationNumber());
            TextField modelField = new TextField(selectedPair.getVehicleModel());
            TextField typeField = new TextField(selectedPair.getVehicleType());
            TextField timeField = new TextField(String.valueOf(selectedPair.getVehicleDuration()));

            gridPane.add(new Label("Nom du véhicule :"), 0, 0);
            gridPane.add(nameField, 1, 0);
            gridPane.add(new Label("Numéro d'immatriculation :"), 0, 1);
            gridPane.add(registrationField, 1, 1);
            gridPane.add(new Label("Modèle :"), 0, 2);
            gridPane.add(modelField, 1, 2);
            gridPane.add(new Label("Type :"), 0, 3);
            gridPane.add(typeField, 1, 3);
            gridPane.add(new Label("Durée (jours) :"), 0, 4);
            gridPane.add(timeField, 1, 4);

            dialog.getDialogPane().setContent(gridPane);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            // Afficher la boîte de dialogue et récupérer les valeurs
            dialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    try {
                        // Modifier les propriétés du véhicule
                        selectedPair.getVehicle().setName(nameField.getText());
                        selectedPair.getVehicle().setRegistrationNumber(registrationField.getText());
                        selectedPair.getVehicle().setModel(modelField.getText());
                        selectedPair.getVehicle().setType(typeField.getText());
                        selectedPair.getVehicle().setDuration(Integer.parseInt(timeField.getText()));

                        table.refresh(); // Rafraîchir la table pour afficher les nouvelles données
                    } catch (NumberFormatException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Valeur invalide");
                        alert.setContentText("Veuillez entrer un nombre valide pour la durée.");
                        alert.showAndWait();
                    }
                }
            });
        } else {
            // Afficher une alerte si aucun élément n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Aucun élément sélectionné");
            alert.setContentText("Veuillez sélectionner un élément à modifier.");
            alert.showAndWait();
        }
    }
    public void SaveToFile(ActionEvent e) {
        // Ouvrir un FileChooser pour choisir l'emplacement du fichier de sauvegarde
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
        fileChooser.setInitialFileName("vehicles_data.csv");

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Définir les largeurs de colonnes
                int colWidthName = 20;
                int colWidthReg = 25;
                int colWidthModel = 15;
                int colWidthType = 15;
                int colWidthDuration = 10;
                int colWidthSubscription = 15;
                int colWidthPrice = 10;
                int colWidthFullName = 25;
                int colWidthNID = 15;

                // Écrire les en-têtes de colonnes
                String header = String.format(
                        "%-" + colWidthName + "s | %-" + colWidthReg + "s | %-" + colWidthModel + "s | %-" + colWidthType + "s | %-" + colWidthDuration + "s | %-" + colWidthSubscription + "s | %-" + colWidthPrice + "s | %-" + colWidthFullName + "s | %-" + colWidthNID + "s",
                        "Nname", "Registration number", "Modele", "Type", "Time(h)", "subscription", "Price", "Full name", "NID"
                );
                writer.write(header);
                writer.newLine();
                writer.write("-".repeat(header.length())); // Ligne séparatrice
                writer.newLine();

                // Parcourir les données et écrire chaque ligne
                for (CustomerVehiclePair pair : data) {
                    String line = String.format(
                            "%-" + colWidthName + "s | %-" + colWidthReg + "s | %-" + colWidthModel + "s | %-" + colWidthType + "s | %-" + colWidthDuration + "d | %-" + colWidthSubscription + "s | %-" + colWidthPrice + "d | %-" + colWidthFullName + "s | %-" + colWidthNID + "s",
                            pair.getVehicle().getName(),
                            pair.getVehicle().getRegistrationNumber(),
                            pair.getVehicle().getModel(),
                            pair.getVehicle().getType(),
                            pair.getVehicle().getDuration(),
                            pair.getVehicle().isSubscription() ? "Oui" : "Non",
                            pair.getVehicle().getPrice(),
                            pair.getCustomerFullName(),
                            pair.getCustomerNid()
                    );
                    writer.write(line);
                    writer.newLine();
                }

                // Confirmation de sauvegarde
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Saved Data");
                alert.setContentText("The vehicle data has been saved in the file " + file.getAbsolutePath());
                alert.showAndWait();

            } catch (IOException ex) {
                // Gestion des erreurs
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la sauvegarde");
                alert.setContentText("Impossible de sauvegarder les données dans le fichier.");
                alert.showAndWait();
                ex.printStackTrace();
            }
        } else {
            // Si aucun fichier n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun fichier sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir un emplacement pour enregistrer le fichier.");
            alert.showAndWait();
        }
    }




    public void LoadFromFile(ActionEvent event) {
        // Créez un FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier CSV");

        // Ajoutez un filtre pour les fichiers .csv
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );

        // Ouvrez le sélecteur de fichiers
        Stage stage = (Stage) scenepane.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                boolean isFirstLine = true;
                ObservableList<CustomerVehiclePair> loadedData = FXCollections.observableArrayList();

                // Lire chaque ligne
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false; // Ignorer la première ligne (les en-têtes)
                        continue;
                    }

                    // Divisez la ligne en champs
                    String[] fields = line.split(",");

                    // Validation : vérifier que nous avons le bon nombre de champs
                    if (fields.length == 9) {
                        Customer customer = new Customer(fields[7].trim(), fields[8].trim(), fields[6].trim());
                        Vehicle vehicle = new Car(
                                fields[0].trim(),
                                fields[1].trim(),
                                fields[2].trim(),
                                fields[3].trim(),
                                Integer.parseInt(fields[4].trim()),
                                fields[5].trim().equalsIgnoreCase("Oui")

                        );
                        CustomerVehiclePair pair = new CustomerVehiclePair(customer, vehicle);
                        loadedData.add(pair);
                    }
                }

                // Mettre à jour les données de la table
                this.data = loadedData;
                table.setItems(data);

                // Confirmation de chargement réussi
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Chargement terminé");
                alert.setContentText("Les données ont été chargées depuis le fichier " + selectedFile.getName());
                alert.showAndWait();

            } catch (IOException e) {
                showError("Erreur lors de la lecture du fichier : " + e.getMessage());
            } catch (NumberFormatException e) {
                showError("Le fichier contient des données non valides (exemple : durée ou prix non numériques).");
            }
        } else {
            // Alerte si aucun fichier n'a été sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun fichier sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un fichier à charger.");
            alert.showAndWait();
        }
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    public void initialize() {
        // Mapping des colonnes aux propriétés de l'objet
        RegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        Name.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        Model.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        Type.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        Time.setCellValueFactory(new PropertyValueFactory<>("vehicleDuration"));
        Subscription.setCellValueFactory(new PropertyValueFactory<>("vehicleSubscription"));
        Price.setCellValueFactory(new PropertyValueFactory<>("vehiclePrice"));
        FullName.setCellValueFactory(new PropertyValueFactory<>("customerFullName"));
        NID.setCellValueFactory(new PropertyValueFactory<>("customerNid"));

        // Données d'exemple
        data = FXCollections.observableArrayList(
                new CustomerVehiclePair(
                        new Customer("John", "Doe", "12345"),
                        new Car("Toyota", "Corolla", "ABC123", "Sedan", 7, false, null)
                ),
                new CustomerVehiclePair(
                        new Customer("Jane", "Smith", "67890"),
                        new Car("Ford", "Fiesta", "XYZ789", "Hatchback", 0, true, "1 month")
                ),
                new CustomerVehiclePair(
                        new Customer("Alice", "Johnson", "11223"),
                        new Car("Honda", "Civic", "LMN456", "Sedan", 15, false, null)
                ),
                new CustomerVehiclePair(
                        new Customer("Bob", "Williams", "44556"),
                        new Car("Chevrolet", "Cruze", "OPQ321", "Sedan", 0, true, "3 days")
                ),
                new CustomerVehiclePair(
                        new Customer("Emily", "Davis", "77889"),
                        new Car("Nissan", "Altima", "RST654", "Sedan", 10, false, null)
                ),
                new CustomerVehiclePair(
                        new Customer("David", "Brown", "99887"),
                        new Car("Hyundai", "Elantra", "UVW987", "Hatchback", 0, true, "1 week")
                ),
                new CustomerVehiclePair(
                        new Customer("Sophia", "Miller", "55667"),
                        new Car("Kia", "Optima", "XYZ123", "SUV", 8, false, null)
                ),
                new CustomerVehiclePair(
                        new Customer("Liam", "Anderson", "33445"),
                        new Car("Mazda", "CX-5", "DEF456", "SUV", 0, true, "2 weeks")
                ),
                new CustomerVehiclePair(
                        new Customer("Olivia", "Taylor", "66778"),
                        new Car("Volkswagen", "Golf", "HIJ789", "Hatchback", 18, false, null)
                )
        );

        // Définir les données dans le tableau
        table.setItems(data);
    }

    @FXML
    public void search(ActionEvent event) {
        // Créer une liste filtrée basée sur les données existantes
        FilteredList<CustomerVehiclePair> filteredData = new FilteredList<>(data, b -> true);

        // Ajouter un listener pour filtrer la table dynamiquement
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pair -> {
                // Si aucun texte n'est entré, afficher toutes les données
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Vérifier les critères de recherche dans les colonnes pertinentes
                if (pair.getVehicleRegistrationNumber().toLowerCase().contains(lowerCaseFilter)
                        || pair.getVehicleName().toLowerCase().contains(lowerCaseFilter)
                        || pair.getVehicleModel().toLowerCase().contains(lowerCaseFilter)
                        || pair.getCustomerFullName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Correspondance trouvée
                }
                return false; // Pas de correspondance
            });
        });

        // Appliquer les données filtrées au tableau
        table.setItems(filteredData);
    }

    public void exit(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avertissement !");
        alert.setHeaderText("Confirmer la sortie");
        alert.setContentText("Êtes-vous sûr de vouloir quitter ?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) scenepane.getScene().getWindow();
            stage.close();
        }
    }
}
