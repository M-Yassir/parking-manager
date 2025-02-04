package com.example.demo;
public class Bike extends Vehicle {
    public Bike() {
        super();
    }

    public Bike(String name, String model, String registrationNumber, String type, int duration) {
        super(name, model, registrationNumber, type, duration);
        this.price = (int) (2*this.duration);
    }


    public Bike(String name, String model, String registrationNumber, String type, int duration, String subscriptionDetails) {
        super(name, model, registrationNumber, type, duration);
        this.price = (int)  (2 * this.duration);
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
        System.out.println("Bike Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Manufacture Date: " + getDuration());
        System.out.println("Price: " + getPrice() + " DH");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
