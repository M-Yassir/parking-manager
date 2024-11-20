package parking_managment_system.Code;

public class Truck extends Vehicle {
    private boolean hasCargoBed;

    public Truck() {
        super();
        this.hasCargoBed = false;
    }

    public Truck(String name, String model, String registrationNumber, String type, String date, int price, boolean hasCargoBed) {
        super(name, model, registrationNumber, type, date, price);
        this.hasCargoBed = hasCargoBed;
    }

    public boolean isHasCargoBed() {
        return hasCargoBed;
    }

    public void setHasCargoBed(boolean hasCargoBed) {
        this.hasCargoBed = hasCargoBed;
    }

    @Override
    public void displayDetails() {
        System.out.println("Truck Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Manufacture Date: " + getDate());
        System.out.println("Price: " + getPrice() + " DH");
        System.out.println("Has Cargo Bed: " + (hasCargoBed ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return super.toString() + ", hasCargoBed=" + hasCargoBed + "]";
    }
}
