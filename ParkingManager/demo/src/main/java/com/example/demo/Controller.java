package com.example.demo;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller {
    // This is for the exit button don't see that
    @FXML
    private Button exit;
    @FXML
    private AnchorPane scenepane;
    Stage stage;

    //---------------------------------------------------------------------------------------------------------

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
    public void search(ActionEvent e){
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

    // the exit method: done
    public void exit(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Confirm exit");
        alert.setContentText("are you sure you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenepane.getScene().getWindow();
            System.out.println("program exit sucessfuly");
            stage.close();
        }
    }


    //---------------------------------------------------------------------------------------------------------

    // this is for the table content focus only on that:

    @FXML private TableView<CustomerVehiclePair> table;

    // Nested Vehicle Columns
    @FXML private TableColumn<CustomerVehiclePair, String> RegistrationNumber;
    @FXML private TableColumn<CustomerVehiclePair, String> Name;
    @FXML private TableColumn<CustomerVehiclePair, String> Model;
    @FXML private TableColumn<CustomerVehiclePair, String> Type;
    @FXML private TableColumn<CustomerVehiclePair, Integer> Time;
    @FXML private TableColumn<CustomerVehiclePair, String> Subscription;
    @FXML private TableColumn<CustomerVehiclePair, Integer> Price;

    // Nested Customer Columns
    @FXML private TableColumn<CustomerVehiclePair, String> FullName;
    @FXML private TableColumn<CustomerVehiclePair, String> NID;

    private ObservableList<CustomerVehiclePair> data;
    @FXML
    public void initialize() {
        System.out.println("Initializing controller..."); // Debugging

        // Map Vehicle columns
        RegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        Name.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        Model.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        Type.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        Time.setCellValueFactory(new PropertyValueFactory<>("vehicleDuration"));
        Subscription.setCellValueFactory(new PropertyValueFactory<>("vehicleSubscription"));
        Price.setCellValueFactory(new PropertyValueFactory<>("vehiclePrice"));

        // Map Customer columns
        FullName.setCellValueFactory(new PropertyValueFactory<>("customerFullName"));
        NID.setCellValueFactory(new PropertyValueFactory<>("customerNid"));

        // Sample data
        data = FXCollections.observableArrayList(
                new CustomerVehiclePair(
                        new Customer("John", "Doe", "12345"),
                        new Car("Toyota", "Corolla", "ABC123", "Sedan", 7, true)
                ),
                new CustomerVehiclePair(
                        new Customer("Jane", "Smith", "67890"),
                        new Car("Ford", "Fiesta", "XYZ789", "Hatchback", 30, false)
                )
        );

        System.out.println("Data size: " + data.size()); // Debugging

        if (data.isEmpty()) {
            System.out.println("Data is empty! Check your initialization.");
        } else {
            for (CustomerVehiclePair pair : data) {
                System.out.println("Customer: " + pair.getCustomerFullName() + ", Vehicle: " + pair.getVehicleName());
            }
        }

        // Set data to the table
        table.setItems(data);
    }
}


