package com.example.demo;

import java.util.HashMap;

public abstract class Vehicle {
    protected String name;
    protected String model;
    protected String registrationNumber; // (matricule)
    protected String type;
    protected int duration;
    protected boolean subscription;
    protected String subscriptionDetails;
    protected int price;

    protected HashMap<String, Integer> SubscriptionPlan = new HashMap<>() {{
        put("1 day", 15); // if it is one day you pay 15dh......
        put("3 days", 30);
        put("1 week", 60);
        put("2 weeks", 110);
        put("1 month", 200);
        put("2 months", 380);
        put("3 months", 550);
        put("6 months", 1000);
    }};

    public Vehicle() {
        this.name = "Unknown";
        this.model = "Unknown";
        this.registrationNumber = "Unknown";
        this.type = "Unknown";
        this.duration = 0;
        this.subscription = false;
        this.subscriptionDetails = null;
        this.price = 0;
    }

    public Vehicle(String name, String model, String registrationNumber, String type, int duration) {
        this.name = name;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.duration = duration;
        this.subscription = false;
        this.subscriptionDetails = null;
        this.price = calculatePrice();
    }

    public Vehicle(String name, String model, String registrationNumber, String type, int price, boolean subscription, String subscriptionDetails) {
        this.name = name;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.duration = 0;
        this.subscription = subscription;
        this.subscriptionDetails = subscriptionDetails;
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public String getSubscriptionDetails() {
        return subscriptionDetails;
    }

    public void setSubscriptionDetails(String subscriptionDetails) {
        this.subscriptionDetails = subscriptionDetails;
    }

    public int getSubscriptionPrice(String plan) {
        return SubscriptionPlan.getOrDefault(plan, -1);
    }

    // Calculate price based on vehicle type and duration
    protected int calculatePrice() {
        switch (type) {
            case "Bike":
                return 2 * duration;
            case "Car":
                return 3 * duration;
            case "Truck":
                return 4 * duration;
            default:
                return 0;
        }
    }

    public int getVehicleDuration() {
        return duration;
    }

    public abstract void displayDetails();

    @Override
    public String toString() {
        return "Vehicle [name=" + name + ", model=" + model + ", registrationNumber=" + registrationNumber
                + ", type=" + type + ", duration=" + duration + ", price=" + price
                + ", subscription=" + subscription + ", subscriptionDetails=" + subscriptionDetails + "]";
    }
}