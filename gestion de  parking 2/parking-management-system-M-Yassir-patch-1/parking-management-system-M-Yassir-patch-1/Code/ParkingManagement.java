package parking_managment_system.Code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingManagement {
    private int places = 0;
    private List<Vehicle> vehicleList = new ArrayList<>(); // List of parked vehicles
    private Scanner scanner = new Scanner(System.in); // Scanner for user input

    // Method to add a vehicle
    public void addVehicle() {
        if(places<3) {
            System.out.println("Enter customer details:");
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();

            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();

            System.out.println("Enter NID (National ID): ");
            String nid = scanner.nextLine();

            Customer customer = new Customer(firstName, lastName, nid);

            System.out.println("Enter vehicle type (1: Bike, 2: Car, 3: Truck): ");
            int type = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("Enter name: ");
            String name = scanner.nextLine();

            System.out.println("Enter model: ");
            String model = scanner.nextLine();

            System.out.println("Enter registration number: ");
            String registrationNumber = scanner.nextLine();

            System.out.println("Duration: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            switch (type) {
                case 1 -> { // Add a Bike
                    System.out.println("Enter max speed: ");
                    int maxSpeed = scanner.nextInt();
                    scanner.nextLine();
                    Bike bike = new Bike(name, model, registrationNumber, "Bike", duration, maxSpeed);
                    System.out.println("You should pay " + bike.price + "DH");
                    vehicleList.add(bike);

                }
                case 2 -> { // Add a Car
                    System.out.println("Has four places? (1: Yes, 0: No): ");
                    boolean hasFourPlaces = scanner.nextInt() == 1;
                    scanner.nextLine();
                    Car car = new Car(name, model, registrationNumber, "Bike", duration, hasFourPlaces);
                    System.out.println("You should pay " + car.price + "DH");
                    vehicleList.add(car);

                }
                case 3 -> { // Add a Truck
                    System.out.println("Has cargo bed? (1: Yes, 0: No): ");
                    boolean hasCargoBed = scanner.nextInt() == 1;
                    scanner.nextLine();
                    Truck truck = new Truck(name, model, registrationNumber, "Truck", duration, hasCargoBed);
                    System.out.println("You should pay " + truck.price + "DH");
                    vehicleList.add(truck);
                }
                default -> System.out.println("Invalid vehicle type!");
            }

            System.out.println("Vehicle added successfully for customer: " + customer.getFirstName() + " " + customer.getLastName());
            places += 1;
        }
        else{
            System.out.println("No available places");
        }
    }

    // Method to modify a vehicle's details
    public void modifyVehicle() {
        System.out.println("Enter registration number of the vehicle to modify: ");
        String registrationNumber = scanner.nextLine();

        for (Vehicle v : vehicleList) {
            if (v.getRegistrationNumber().equals(registrationNumber)) {
                System.out.println("Enter new price: ");
                int price = scanner.nextInt();
                v.setPrice(price);

                System.out.println("Vehicle details updated successfully!");
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }

    // Method to delete a vehicle
    public void deleteVehicle() {
        System.out.println("Enter registration number of the vehicle to delete: ");
        String registrationNumber = scanner.nextLine();

        if (vehicleList.removeIf(v -> v.getRegistrationNumber().equals(registrationNumber))) {
            places-=1;
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    // Method to save the parking list to a file
    public void saveToFile() {
        System.out.println("Enter file name to save the parking list: ");
        String fileName = scanner.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Vehicle v : vehicleList) {
                writer.write(v.toString() + "\n");
            }
            System.out.println("Parking list saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }

    // Method to display all vehicles in the parking lot
    public void displayVehicles() {
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles in the parking lot!");
        } else {
            for (Vehicle v : vehicleList) {
                v.displayDetails();
            }
        }
    }
}