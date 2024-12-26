package com.example.demo;

public class CustomerVehiclePair {
    private final Customer customer;
    private final Vehicle vehicle;

    public CustomerVehiclePair(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
    }
    public boolean isVehicleSubscription() {
        return vehicle != null && vehicle.isVehicleSubscription();
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

    public String getVehicleDuration() {
        // If subscription is true, return "-"
        return vehicle.isSubscription() ? "-" : String.valueOf(vehicle.getVehicleDuration());
    }

    public String getVehicleSubscription() {
        // If subscription is true, return subscription details
        // If not, return "-"
        return vehicle.isSubscription() ? vehicle.getSubscriptionDetails() : "-";
    }
    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getVehiclePrice() {
        return vehicle.getPrice();
    }
}

