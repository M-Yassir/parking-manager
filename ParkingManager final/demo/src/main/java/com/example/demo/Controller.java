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
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.io.*;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;

public class Controller {

    @FXML
    private AnchorPane scenepane;
    @FXML
    private TableView<CustomerVehiclePair> table;
    @FXML
    private TextField searchField;
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
    private Stage stage; // Add a Stage reference

    // Method to set the Stage
    public void setStage(Stage stage) {
        this.stage = stage;
        // Attach the onCloseRequest event handler
        stage.setOnCloseRequest(this::handleCloseRequest);
    }

    // This is for the main buttons
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
            data.remove(selectedPair);
            table.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }

    public void modify(ActionEvent e) {
        CustomerVehiclePair selectedPair = table.getSelectionModel().getSelectedItem();
        if (selectedPair != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Modify Vehicle");
            dialog.setHeaderText("Modify Vehicle Information");

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            TextField nameField = new TextField(selectedPair.getVehicleName());
            TextField registrationField = new TextField(selectedPair.getVehicleRegistrationNumber());
            TextField modelField = new TextField(selectedPair.getVehicleModel());
            TextField typeField = new TextField(selectedPair.getVehicleType());
            TextField timeField = new TextField(String.valueOf(selectedPair.getVehicleDuration()));

            gridPane.add(new Label("Vehicle Name:"), 0, 0);
            gridPane.add(nameField, 1, 0);
            gridPane.add(new Label("Registration Number:"), 0, 1);
            gridPane.add(registrationField, 1, 1);
            gridPane.add(new Label("Model:"), 0, 2);
            gridPane.add(modelField, 1, 2);
            gridPane.add(new Label("Type:"), 0, 3);
            gridPane.add(typeField, 1, 3);
            gridPane.add(new Label("Duration (days):"), 0, 4);
            gridPane.add(timeField, 1, 4);

            dialog.getDialogPane().setContent(gridPane);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    try {
                        selectedPair.getVehicle().setName(nameField.getText());
                        selectedPair.getVehicle().setRegistrationNumber(registrationField.getText());
                        selectedPair.getVehicle().setModel(modelField.getText());
                        selectedPair.getVehicle().setType(typeField.getText());
                        selectedPair.getVehicle().setDuration(Integer.parseInt(timeField.getText()));
                        table.refresh();
                    } catch (NumberFormatException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Invalid Value");
                        alert.setContentText("Please enter a valid number for duration.");
                        alert.showAndWait();
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select an item to modify.");
            alert.showAndWait();
        }
    }

    public void SaveToFile(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setInitialFileName("vehicles_data.csv");

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write header line
                writer.write("RegistrationNumber,Name,Model,Type,Time,Subscription,Price,FullName,NID");
                writer.newLine();

                // Write data rows
                for (CustomerVehiclePair pair : data) {
                    String line = String.join(",",
                            pair.getVehicleRegistrationNumber(),
                            pair.getVehicleName(),
                            pair.getVehicleModel(),
                            pair.getVehicleType(),
                            String.valueOf(pair.getVehicleDuration()),
                            pair.getVehicleSubscription(),
                            String.valueOf(pair.getVehiclePrice()),
                            pair.getCustomerFullName(),
                            pair.getCustomerNid()
                    );
                    writer.write(line);
                    writer.newLine();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Saved Data");
                alert.setContentText("The vehicle data has been saved in the file " + file.getAbsolutePath());
                alert.showAndWait();

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error while saving");
                alert.setContentText("Unable to save data to file.");
                alert.showAndWait();
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No file selected");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a location to save the file.");
            alert.showAndWait();
        }
    }

    public void LoadFromFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose CSV File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        Stage currentStage = (stage != null) ? stage : (Stage) scenepane.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(currentStage);

        if (selectedFile != null) {
            ObservableList<CustomerVehiclePair> loadedData = FXCollections.observableArrayList();
            int lineNumber = 0;
            String currentLine = null; // For error reporting

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String headerLine = reader.readLine(); // Read and discard header
                lineNumber++;
                if (headerLine == null) {
                    showError("Error: The selected file appears to be empty.");
                    return;
                }

                while ((currentLine = reader.readLine()) != null) {
                    lineNumber++;
                    if (currentLine.trim().isEmpty()) {
                        continue; // Skip blank lines
                    }

                    String[] fields = currentLine.split(",");

                    if (fields.length == 9) {
                        String registrationNumber = fields[0].trim();
                        String name = fields[1].trim();
                        String model = fields[2].trim();
                        String type = fields[3].trim();
                        String durationStr = fields[4].trim(); // Get duration string
                        String subscription = fields[5].trim();
                        String fullName = fields[7].trim();
                        String nid = fields[8].trim();

                        if (registrationNumber.isEmpty() || name.isEmpty() || model.isEmpty() || type.isEmpty() || fullName.isEmpty() || nid.isEmpty()) {
                            throw new IllegalArgumentException("Required fields (RegNo, Name, Model, Type, FullName, NID) cannot be empty.");
                        }

                        // --- Modified Parse Duration ---
                        int duration;
                        if (durationStr.equals("-")) {
                            // If the duration field contains "-", treat it as 0 duration
                            duration = 0;
                        } else {
                            // Otherwise, try to parse it as a non-negative integer
                            try {
                                duration = Integer.parseInt(durationStr);
                                if (duration < 0) {
                                    // Throw error if parsed value is negative
                                    throw new NumberFormatException("Duration cannot be negative: " + duration);
                                }
                            } catch (NumberFormatException e) {
                                // Throw specific error if duration is not a valid non-negative integer (and wasn't "-")
                                throw new NumberFormatException("Invalid non-negative integer value for Duration: '" + durationStr + "'");
                            }
                        }
                        // --- End of Modified Parse Duration ---


                        String firstName = "";
                        String lastName = "";
                        if (!fullName.isEmpty()) {
                            String[] nameParts = fullName.split("\\s+", 2);
                            firstName = nameParts[0];
                            lastName = nameParts.length > 1 ? nameParts[1] : "";
                        }

                        Customer customer = new Customer(firstName, lastName, nid);

                        Vehicle vehicle;
                        String vehicleTypeLower = type.toLowerCase();
                        boolean hasSubscription = subscription != null && !subscription.isEmpty() && !subscription.equals("-");

                        switch (vehicleTypeLower) {
                            case "bike":
                                vehicle = new Bike(name, model, registrationNumber, type, duration, hasSubscription ? subscription : null);
                                break;
                            case "truck":
                                if (hasSubscription) {
                                    System.err.println("Warning line " + lineNumber + ": Subscription '" + subscription + "' found for Truck type, ignoring.");
                                }
                                vehicle = new Truck(name, model, registrationNumber, type, duration);
                                break;
                            case "car":
                                vehicle = new Car(name, model, registrationNumber, type, duration, hasSubscription ? subscription : null);
                                break;
                            default:
                                throw new IllegalArgumentException("Unknown vehicle type: '" + type + "'");
                        }

                        CustomerVehiclePair pair = new CustomerVehiclePair(customer, vehicle);
                        loadedData.add(pair);

                    } else {
                        throw new IllegalArgumentException("Incorrect number of fields found. Expected 9, got " + fields.length + ".");
                    }
                } // End while loop

                if (this.data == null) {
                    this.data = FXCollections.observableArrayList();
                    table.setItems(this.data);
                }
                searchField.clear();
                this.data.setAll(loadedData);
                table.refresh();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Loading Complete");
                alert.setContentText("Successfully loaded " + loadedData.size() + " records from file:\n" + selectedFile.getName());
                alert.showAndWait();

            } catch (IOException e) {
                showError("Error reading file: " + e.getMessage());
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                showError("Error processing file at line " + lineNumber + ": " + e.getMessage() + "\nLine content: [" + currentLine + "]");
                e.printStackTrace();
            } catch (Exception e) {
                showError("An unexpected error occurred near line " + lineNumber + ": " + e.getMessage() + "\nLine content: [" + currentLine + "]");
                e.printStackTrace();
            }
        } else {
            System.out.println("File load cancelled by user.");
        }
    }

    // Ensure you have the showError method defined as before
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Loading Error");
        alert.setHeaderText("Failed to load data");
        TextArea textArea = new TextArea(message);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        alert.getDialogPane().setContent(textArea);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setResizable(true);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        // Mapping des colonnes aux propriétés de l'objet
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
                        new Car("Toyota", "Corolla", "ABC123", "Car", 7)
                ),
                new CustomerVehiclePair(
                        new Customer("Jane", "Smith", "67890"),
                        new Bike("Honda", "CBR", "XYZ789", "Bike", 0, "1 month")
                ),
                new CustomerVehiclePair(
                        new Customer("Alice", "Johnson", "11223"),
                        new Truck("Scania", "P380", "LMN456", "Truck", 15)
                ),
                new CustomerVehiclePair(
                        new Customer("Bob", "Williams", "44556"),
                        new Bike("Specialized", "Rockhopper", "OPQ321", "Bike", 0, "3 days")
                ),
                new CustomerVehiclePair(
                        new Customer("Emily", "Davis", "77889"),
                        new Truck("Ford", "F-150", "RST654", "Truck", 10)
                ),
                new CustomerVehiclePair(
                        new Customer("David", "Brown", "99887"),
                        new Bike("Kawasaki", "Ninja", "UVW987", "Bike", 0, "1 week")
                ),
                new CustomerVehiclePair(
                        new Customer("Sophia", "Miller", "55667"),
                        new Truck("Mercedes-Benz", "Actros", "XYZ123", "Truck", 8)
                ),
                new CustomerVehiclePair(
                        new Customer("Liam", "Anderson", "33445"),
                        new Car("BMW", "3 Series", "DEF456", "Car", 0, "2 weeks")
                ),
                new CustomerVehiclePair(
                        new Customer("Olivia", "Taylor", "66778"),
                        new Car("Nissan", "Altima", "HIJ789", "Car", 18)
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

    private void handleCloseRequest(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to quit?");

        // If the user cancels the exit, consume the event to prevent the window from closing
        if (alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            event.consume();
        }
    }

    @FXML
public void exit(ActionEvent e) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Warning!");
    alert.setHeaderText("Confirm Exit");
    alert.setContentText("Are you sure you want to quit?");
    if (alert.showAndWait().get() == ButtonType.OK) {
        // Get the stage from the current scene
        Stage stage = (Stage) scenepane.getScene().getWindow();
        stage.close();
    }
}
}

