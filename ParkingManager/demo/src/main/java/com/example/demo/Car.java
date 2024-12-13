package com.example.demo;

public class Car extends Vehicle {
    private boolean HasFourDoors;
    private float CarCoeficient = 1;
    private String subscriptionDetails;

    public Car() {
        super();
        this.HasFourDoors = false;
    }

    public Car(String name, String model, String registrationNumber, String type, int duration, boolean HasFourDoors, String subscriptionDetails) {
        super(name, model, registrationNumber, type, duration);
        this.HasFourDoors = HasFourDoors;
        this.price = (int) CarCoeficient * (3 * this.duration);
        this.subscriptionDetails = subscriptionDetails;

        // Adjust price and duration based on subscription
        if (subscriptionDetails != null) {
            this.duration = 0; // Set duration to 0 if subscription is selected
            this.price = getSubscriptionPrice(subscriptionDetails);
            this.subscription = true;
        }
    }

    public boolean isHasFourDoors() {
        return HasFourDoors;
    }

    public void setHasFourDoors(boolean HasFourDoors) {
        this.HasFourDoors = HasFourDoors;
    }

    public void setCarCoeficient() {
        if (HasFourDoors) {
            this.CarCoeficient = (float) (this.CarCoeficient + 0.2);
        }
    }

    public float getCarCoeficient() {
        return CarCoeficient;
    }

    @Override
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Manufacture Date: " + getDuration());
        System.out.println("Price: " + getPrice() + " DH");
        System.out.println("Has Sunroof: " + (HasFourDoors ? "Yes" : "No"));
    }

    public String getSubscriptionDetails() {
        return subscriptionDetails != null ? subscriptionDetails : "-";
    }

    @Override
    public String toString() {
        return super.toString() + ", HasFourDoors=" + HasFourDoors + "]";
    }
}