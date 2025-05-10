package parking_managment_system.Code;

import java.util.Scanner;

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

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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