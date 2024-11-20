package parking_managment_system.Code;
public class Bike extends Vehicle {
    private int maxSpeed;

    public Bike() {
        super();
        this.maxSpeed = 0;
    }

    public Bike(String name, String model, String registrationNumber, String type, String date, int price, int maxSpeed) {
        super(name, model, registrationNumber, type, date, price);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void displayDetails() {
        System.out.println("Bike Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Manufacture Date: " + getDate());
        System.out.println("Price: " + getPrice() + " DH");
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }

    @Override
    public String toString() {
        return super.toString() + ", maxSpeed=" + maxSpeed + "]";
    }
}
