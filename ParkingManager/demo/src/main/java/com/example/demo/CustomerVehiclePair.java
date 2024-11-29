package com.example.demo;

public class CustomerVehiclePair {
    private final Customer customer;
    private final Vehicle vehicle;

    public CustomerVehiclePair(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
    }

    // Customer Properties
    public String getCustomerFullName() {
        return customer.getFirstName() + " " + customer.getLastName();
    }

    public String getCustomerNid() {
        return customer.getNid();
    }

    // Vehicle Properties
    public String getVehicleRegistrationNumber() {
        return vehicle.getRegistrationNumber();
    }

    public String getVehicleName() {
        return vehicle.getName();
    }

    public String getVehicleModel() {
        return vehicle.getModel();
    }

    public String getVehicleType() {
        return vehicle.getType();
    }

    public int getVehicleDuration() {
        return vehicle.getDuration();
    }

    public int getVehiclePrice() {
        return vehicle.getPrice();
    }

    public String getVehicleSubscription() {
        return "1 Month: " + vehicle.getSubscriptionPrice("1 month") + " DH"; // Example summary
    }
}

