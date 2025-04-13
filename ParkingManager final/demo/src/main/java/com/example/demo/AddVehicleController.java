package com.example.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddVehicleController {

    @FXML
    private TextField registrationNumberTextField;

    @FXML
    private TextField vehicleNameTextField;

    @FXML
    private TextField vehicleModelTextField;

    @FXML
    private ComboBox<String> vehicleTypeComboBox;

    @FXML
    private TextField customerFullNameTextField;

    @FXML
    private TextField customerNidTextField;

    @FXML
    private CheckBox subscriptionCheckBox;

    @FXML
    private ComboBox<String> subscriptionComboBox;

    @FXML
    private TextField timeTextField;

    @FXML
    private Label totalAmountLabel;

    private Vehicle vehicle;

    // Reference to the main controller to pass the new vehicle
    private Controller mainController;

    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    @FXML
    public void initialize() {
        // Initialize ComboBoxes
        vehicleTypeComboBox.setItems(FXCollections.observableArrayList("Bike", "Car", "Truck"));
        subscriptionComboBox.setItems(FXCollections.observableArrayList(
                "1 day", "3 days", "1 week", "2 weeks", "1 month", "2 months", "3 months", "6 months"));

        // Set Subscription logic
        subscriptionComboBox.setDisable(true);
        timeTextField.setDisable(false);

        subscriptionCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                subscriptionComboBox.setDisable(false);
                timeTextField.setDisable(true);
                updatePrice();
            } else {
                subscriptionComboBox.setDisable(true);
                timeTextField.setDisable(false);
                updatePrice();
            }
        });

        // Listen to changes in timeTextField
        timeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!subscriptionCheckBox.isSelected()) {
                updatePrice();
            }
        });

        // Listen to changes in subscriptionComboBox
        subscriptionComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (subscriptionCheckBox.isSelected()) {
                updatePrice();
            }
        });

        // Listen to changes in vehicleTypeComboBox
        vehicleTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            updatePrice();
        });

        // Initialize a default vehicle instance
        vehicle = new Vehicle() {
            @Override
            public void displayDetails() {
                // Vehicle details logic (not required in this context)
            }
        };
    }

    private void updatePrice() {
        try {
            double price = 0;

            if (subscriptionCheckBox.isSelected()) {
                // Get subscription price
                String selectedPlan = subscriptionComboBox.getValue();
                if (selectedPlan != null) {
                    price = vehicle.getSubscriptionPrice(selectedPlan);
                }
            } else {
                // Calculate price based on time and vehicle type
                int time = Integer.parseInt(timeTextField.getText());
                String vehicleType = vehicleTypeComboBox.getValue();

                if (vehicleType != null) {
                    switch (vehicleType) {
                        case "Bike":
                            price = 2 * time;
                            break;
                        case "Car":
                            price = 3 * time;
                            break;
                        case "Truck":
                            price = 4 * time;
                            break;
                    }
                }
            }

            totalAmountLabel.setText(price > 0 ? String.valueOf(price) : "0.0");
        } catch (NumberFormatException ex) {
            // If input is invalid, reset price to 0
            totalAmountLabel.setText("0.0");
        }
    }

    @FXML
    public void addVehicle() {
        try {
            // Validate fields
            if (registrationNumberTextField.getText().isEmpty() ||
                    vehicleNameTextField.getText().isEmpty() ||
                    vehicleModelTextField.getText().isEmpty() ||
                    vehicleTypeComboBox.getValue() == null ||
                    customerFullNameTextField.getText().isEmpty() ||
                    customerNidTextField.getText().isEmpty() ||
                    (!subscriptionCheckBox.isSelected() && timeTextField.getText().isEmpty()) ||
                    (subscriptionCheckBox.isSelected() && subscriptionComboBox.getValue() == null)) {
                showError("All fields must be filled.");
                return;
            }

            // Validate time field if not using subscription
            int time = 0;
            String subscriptionDetails = null;
            if (!subscriptionCheckBox.isSelected()) {
                try {
                    time = Integer.parseInt(timeTextField.getText());
                } catch (NumberFormatException ex) {
                    showError("Time must be an integer.");
                    return;
                }
            } else {
                time = 0;  // Set time to 0 if subscription is selected
                subscriptionDetails = subscriptionComboBox.getValue();
            }

            // Create the appropriate vehicle type based on the selected type
            Vehicle vehicle;
            String vehicleType = vehicleTypeComboBox.getValue();
            switch (vehicleType) {
                case "Car":
                    vehicle = new Car(
                            vehicleNameTextField.getText(),
                            vehicleModelTextField.getText(),
                            registrationNumberTextField.getText(),
                            vehicleType,
                            time,
                            subscriptionDetails
                    );
                    break;
                case "Truck":
                    vehicle = new Truck(
                            vehicleNameTextField.getText(),
                            vehicleModelTextField.getText(),
                            registrationNumberTextField.getText(),
                            vehicleType,
                            time,
                            subscriptionDetails
                    );
                    break;
                case "Bike":
                    vehicle = new Bike(
                            vehicleNameTextField.getText(),
                            vehicleModelTextField.getText(),
                            registrationNumberTextField.getText(),
                            vehicleType,
                            time,
                            subscriptionDetails
                    );
                    break;
                default:
                    showError("Invalid vehicle type.");
                    return;
            }

            // Set the price from totalAmountLabel to the vehicle
            vehicle.setPrice((int) Double.parseDouble(totalAmountLabel.getText()));

            // Create a Customer object
            Customer customer = new Customer(
                    customerFullNameTextField.getText().split(" ")[0],  // First name
                    customerFullNameTextField.getText().split(" ").length > 1 ?
                            customerFullNameTextField.getText().split(" ")[1] : "",  // Last name
                    customerNidTextField.getText()
            );

            // Create a CustomerVehiclePair and add it to the main controller
            CustomerVehiclePair newPair = new CustomerVehiclePair(customer, vehicle);
            if (mainController != null) {
                mainController.addNewVehicle(newPair);
            }

            System.out.println("Vehicle added successfully!");

            // Close the current stage
            Stage stage = (Stage) registrationNumberTextField.getScene().getWindow();
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            showError("An unexpected error occurred.");
        }
    }


    @FXML
    public void cancel() {
        // Close the current stage
        Stage stage = (Stage) registrationNumberTextField.getScene().getWindow();
        stage.close();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}










