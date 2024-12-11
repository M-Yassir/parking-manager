package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public void add(ActionEvent e){
        // To do
        System.out.println("Add");
    }
    public void remove(ActionEvent e){
        // To do
        System.out.println("Add");
    }
    public void modify(ActionEvent e){
        // To do
        System.out.println("Add");
    }
    public void SaveToFile(ActionEvent e){
        // To do
        System.out.println("Add");
    }
    public void LoadFromFile(ActionEvent e){
        // To do
        System.out.println("Add");
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
                        new Car("Toyota", "Corolla", "ABC123", "Sedan", 7, true)
                ),
                new CustomerVehiclePair(
                        new Customer("Jane", "Smith", "67890"),
                        new Car("Ford", "Fiesta", "XYZ789", "Hatchback", 30, false)
                ),
                new CustomerVehiclePair(
                        new Customer("Alice", "Johnson", "11223"),
                        new Car("Honda", "Civic", "LMN456", "Sedan", 15, true)
                ),
                new CustomerVehiclePair(
                        new Customer("Bob", "Williams", "44556"),
                        new Car("Chevrolet", "Cruze", "OPQ321", "Sedan", 20, false)
                ),
                new CustomerVehiclePair(
                        new Customer("Emily", "Davis", "77889"),
                        new Car("Nissan", "Altima", "RST654", "Sedan", 10, true)
                ),
                new CustomerVehiclePair(
                        new Customer("David", "Brown", "99887"),
                        new Car("Hyundai", "Elantra", "UVW987", "Hatchback", 25, false)
                ),
                new CustomerVehiclePair(
                        new Customer("Sophia", "Miller", "55667"),
                        new Car("Kia", "Optima", "XYZ123", "SUV", 8, true)
                ),
                new CustomerVehiclePair(
                        new Customer("Liam", "Anderson", "33445"),
                        new Car("Mazda", "CX-5", "DEF456", "SUV", 12, false)
                ),
                new CustomerVehiclePair(
                        new Customer("Olivia", "Taylor", "66778"),
                        new Car("Volkswagen", "Golf", "HIJ789", "Hatchback", 18, true)
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
