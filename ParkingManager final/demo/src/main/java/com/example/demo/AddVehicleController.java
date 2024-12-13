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
                // If subscription is selected, set time to 0 and get subscription details
                time = 0;
                subscriptionDetails = subscriptionComboBox.getValue();
            }

            // If all validations pass, create Customer and Vehicle
            Customer customer = new Customer(
                    customerFullNameTextField.getText().split(" ")[0],  // First name
                    customerFullNameTextField.getText().split(" ").length > 1 ?
                            customerFullNameTextField.getText().split(" ")[1] : "",  // Last name
                    customerNidTextField.getText()
            );

            Car car = new Car(
                    vehicleNameTextField.getText(),
                    vehicleModelTextField.getText(),
                    registrationNumberTextField.getText(),
                    vehicleTypeComboBox.getValue(),
                    subscriptionCheckBox.isSelected() ?
                            vehicle.getSubscriptionPrice(subscriptionComboBox.getValue()) :
                            Integer.parseInt(timeTextField.getText()),
                    subscriptionCheckBox.isSelected(),
                    subscriptionDetails  // Pass the subscription details
            );

            CustomerVehiclePair newPair = new CustomerVehiclePair(customer, car);

            // Check if main controller is set before adding
            if (mainController != null) {
                mainController.addNewVehicle(newPair);
            }

            // Log success
            System.out.println("Vehicle added successfully!");
            System.out.printf("Registration: %s, Name: %s, Model: %s, Type: %s, Customer: %s (%s), Subscription: %s, Time: %d, Total: %s%n",
                    registrationNumberTextField.getText(),
                    vehicleNameTextField.getText(),
                    vehicleModelTextField.getText(),
                    vehicleTypeComboBox.getValue(),
                    customerFullNameTextField.getText(),
                    customerNidTextField.getText(),
                    subscriptionCheckBox.isSelected() ? subscriptionComboBox.getValue() : "No subscription",
                    time,
                    totalAmountLabel.getText());

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










