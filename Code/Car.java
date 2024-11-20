package parking_managment_system.Code;

public class Car extends Vehicle {
    private boolean HasFourDoors;

    public Car() {
        super();
        this.HasFourDoors = false;
    }

    public Car(String name, String model, String registrationNumber, String type, String date, int price, boolean HasFourDoors) {
        super(name, model, registrationNumber, type, date, price);
        this.HasFourDoors = HasFourDoors;
    }

    public boolean isHasFourDoors() {
        return HasFourDoors;
    }

    public void setHasFourDoors(boolean HasFourDoors) {
        this.HasFourDoors = HasFourDoors;
    }

    @Override
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Manufacture Date: " + getDate());
        System.out.println("Price: " + getPrice() + " DH");
        System.out.println("Has Sunroof: " + (HasFourDoors ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return super.toString() + ", HasFourDoors=" + HasFourDoors + "]";
    }
}
