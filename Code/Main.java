import java.io.FileWriter; // For saving data to a file
import java.io.IOException; // For handling file I/O exceptions
import java.util.ArrayList; // To use dynamic lists
import java.util.List;
import java.util.Scanner; // For user input

// Abstract class representing a vehicle
abstract class Vehicle {
    // Common attributes for all vehicles
    protected String brand;
    protected String model;
    protected String licensePlate;
    protected int parkingDuration; // Duration in hours
    protected float hourlyRate; // Parking hourly rate
    protected boolean hasLuggage; // Indicates if the vehicle has luggage

    // Constructor to initialize a vehicle
    public Vehicle(String brand, String model, String licensePlate, int parkingDuration, boolean hasLuggage, float hourlyRate) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.parkingDuration = parkingDuration;
        this.hasLuggage = hasLuggage;
        this.hourlyRate = hourlyRate;
    }

    // Abstract method to display vehicle-specific information
    public abstract void displayInfo();

    // Method to calculate parking fees
    public float calculateParkingFee() {
        float fee = parkingDuration * hourlyRate; // Base fee: hours * rate
        if (hasLuggage) {
            fee += getLuggageFee(); // Add extra charge for luggage if applicable
        }
        return fee;
    }

    // Abstract method to calculate luggage fee based on vehicle type
    public abstract float getLuggageFee();
}

// Car class extending Vehicle
class Car extends Vehicle {
    private int numberOfDoors; // Additional attribute specific to cars

    public Car(String brand, String model, String licensePlate, int parkingDuration, boolean hasLuggage, int numberOfDoors) {
        super(brand, model, licensePlate, parkingDuration, hasLuggage, 5.0f); // Hourly rate for cars is 5.0
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car - Brand: " + brand + ", Model: " + model + ", License Plate: " + licensePlate +
                ", Doors: " + numberOfDoors + ", Luggage: " + (hasLuggage ? "Yes" : "No"));
    }

    @Override
    public float getLuggageFee() {
        return 2.0f; // Additional fee for luggage in cars
    }
}

// Truck class extending Vehicle
class Truck extends Vehicle {
    private boolean hasTrailer; // Additional attribute specific to trucks

    public Truck(String brand, String model, String licensePlate, int parkingDuration, boolean hasLuggage, boolean hasTrailer) {
        super(brand, model, licensePlate, parkingDuration, hasLuggage, 10.0f); // Hourly rate for trucks is 10.0
        this.hasTrailer = hasTrailer;
    }

    @Override
    public void displayInfo() {
        System.out.println("Truck - Brand: " + brand + ", Model: " + model + ", License Plate: " + licensePlate +
                ", Trailer: " + (hasTrailer ? "Yes" : "No") + ", Luggage: " + (hasLuggage ? "Yes" : "No"));
    }

    @Override
    public float getLuggageFee() {
        return 5.0f; // Additional fee for luggage in trucks
    }
}

// Motorcycle class extending Vehicle
class Motorcycle extends Vehicle {
    private int maxSpeed; // Additional attribute specific to motorcycles

    public Motorcycle(String brand, String model, String licensePlate, int parkingDuration, boolean hasLuggage, int maxSpeed) {
        super(brand, model, licensePlate, parkingDuration, hasLuggage, 3.0f); // Hourly rate for motorcycles is 3.0
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void displayInfo() {
        System.out.println("Motorcycle - Brand: " + brand + ", Model: " + model + ", License Plate: " + licensePlate +
                ", Max Speed: " + maxSpeed + " km/h, Luggage: " + (hasLuggage ? "Yes" : "No"));
    }

    @Override
    public float getLuggageFee() {
        return 1.0f; // Additional fee for luggage in motorcycles
    }
}

// Parking Management class to handle parking operations
class ParkingManagement {
    private List<Vehicle> vehicleList = new ArrayList<>(); // List of parked vehicles
    private Scanner scanner = new Scanner(System.in); // Scanner for user input

    // Method to add a vehicle
    public void addVehicle() {
        System.out.println("Enter vehicle type (1: Car, 2: Truck, 3: Motorcycle): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter brand: ");
        String brand = scanner.nextLine();

        System.out.println("Enter model: ");
        String model = scanner.nextLine();

        System.out.println("Enter license plate: ");
        String licensePlate = scanner.nextLine();

        System.out.println("Enter parking duration (hours): ");
        int parkingDuration = scanner.nextInt();

        System.out.println("Has luggage? (1: Yes, 0: No): ");
        boolean hasLuggage = scanner.nextInt() == 1;

        switch (type) {
            case 1 -> { // Add a car
                System.out.println("Enter number of doors: ");
                int doors = scanner.nextInt();
                vehicleList.add(new Car(brand, model, licensePlate, parkingDuration, hasLuggage, doors));
            }
            case 2 -> { // Add a truck
                System.out.println("Has trailer? (1: Yes, 0: No): ");
                boolean hasTrailer = scanner.nextInt() == 1;
                vehicleList.add(new Truck(brand, model, licensePlate, parkingDuration, hasLuggage, hasTrailer));
            }
            case 3 -> { // Add a motorcycle
                System.out.println("Enter max speed: ");
                int maxSpeed = scanner.nextInt();
                vehicleList.add(new Motorcycle(brand, model, licensePlate, parkingDuration, hasLuggage, maxSpeed));
            }
            default -> System.out.println("Invalid vehicle type!");
        }

        System.out.println("Vehicle added successfully!");
    }

    // Method to modify a vehicle's details
    public void modifyVehicle() {
        System.out.println("Enter license plate of the vehicle to modify: ");
        String licensePlate = scanner.next();

        for (Vehicle v : vehicleList) {
            if (v.licensePlate.equals(licensePlate)) {
                System.out.println("Modify parking duration: ");
                v.parkingDuration = scanner.nextInt();

                System.out.println("Has luggage? (1: Yes, 0: No): ");
                v.hasLuggage = scanner.nextInt() == 1;

                System.out.println("Vehicle details updated successfully!");
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }

    // Method to delete a vehicle
    public void deleteVehicle() {
        System.out.println("Enter license plate of the vehicle to delete: ");
        String licensePlate = scanner.next();

        vehicleList.removeIf(v -> v.licensePlate.equals(licensePlate)); // Remove vehicle by license plate
        System.out.println("Vehicle removed successfully!");
    }

    // Method to save the parking list to a file
    public void saveToFile() {
        System.out.println("Enter file name to save the parking list: ");
        String fileName = scanner.next();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Vehicle v : vehicleList) {
                writer.write("Vehicle: " + v.getClass().getSimpleName() + ", Brand: " + v.brand + ", Model: " + v.model +
                        ", License Plate: " + v.licensePlate + ", Parking Fee: " + v.calculateParkingFee() + " DH\n");
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
                v.displayInfo();
                System.out.println("Parking Fee: " + v.calculateParkingFee() + " DH");
            }
        }
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        ParkingManagement parking = new ParkingManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Add Vehicle
                    2. Modify Vehicle
                    3. Delete Vehicle
                    4. Display Vehicles
                    5. Save to File
                    6. Exit
                    """);

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> parking.addVehicle(); // Add a new vehicle
                case 2 -> parking.modifyVehicle(); // Modify vehicle details
                case 3 -> parking.deleteVehicle(); // Delete a vehicle
                case 4 -> parking.displayVehicles(); // Display all vehicles
                case 5 -> parking.saveToFile(); // Save the list to a file
                case 6 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
