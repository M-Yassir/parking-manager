package com.example.demo;

public class Car extends Vehicle {
    private String subscriptionDetails;

    public Car() {
        super();
    }
    public Car(String name, String model, String registrationNumber, String type, int duration) {
        super(name, model, registrationNumber, type, duration);
        this.price = (int) (3*this.duration);
    }

    public Car(String name, String model, String registrationNumber, String type, int duration, String subscriptionDetails) {
        super(name, model, registrationNumber, type, duration);
        this.price = (int)  (3 * this.duration);
        this.subscriptionDetails = subscriptionDetails;

        // Adjust price and duration based on subscription
        if (subscriptionDetails != null) {
            this.duration = 0; // Set duration to 0 if subscription is selected
            this.price = getSubscriptionPrice(subscriptionDetails);
            this.subscription = true;
        }
    }



    @Override
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Duration: " + getDuration());
        System.out.println("Price: " + getPrice() + " DH");
    }

    public String getSubscriptionDetails() {
        return subscriptionDetails != null ? subscriptionDetails : "-";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}