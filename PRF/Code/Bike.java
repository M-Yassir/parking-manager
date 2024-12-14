package parking_managment_system.Code;
public class Bike extends Vehicle {
    private int maxSpeed;
    private float BikeCoeficient = 1;
    public Bike() {
        super();
        this.maxSpeed = 0;
    }

    public Bike(String name, String model, String registrationNumber, String type, int duration, int maxSpeed) {
        super(name, model, registrationNumber, type, duration);
        this.maxSpeed = maxSpeed;
        this.price = (int) BikeCoeficient*(3*this.duration);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setBikeCoeficient(){
        if(maxSpeed > 180) {
            this.BikeCoeficient = (float) (this.BikeCoeficient + 0.2);
        }
    }

    public float getBikeCoeficient() {
        return BikeCoeficient;
    }

    @Override
    public void displayDetails() {
        System.out.println("Bike Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Manufacture Date: " + getDuration());
        System.out.println("Price: " + getPrice() + " DH");
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }

    @Override
    public String toString() {
        return super.toString() + ", maxSpeed=" + maxSpeed + "]";
    }
}
