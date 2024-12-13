package com.example.demo;

public class Truck extends Vehicle {
    private boolean HasCargoBed;
    private float TruckCoeficient = 1;
    public Truck() {
        super();
        this.HasCargoBed = false;
    }

    public Truck(String name, String model, String registrationNumber, String type, int duration, boolean hasCargoBed) {
        super(name, model, registrationNumber, type , duration);
        this.HasCargoBed = hasCargoBed;
        this.price = (int) TruckCoeficient*(this.duration*3);
    }

    public boolean isHasCargoBed() {
        return HasCargoBed;
    }

    public void setHasCargoBed(boolean HasCargoBed) {
        this.HasCargoBed = HasCargoBed;
    }

    public void setTruckCoeficient(){
        if(HasCargoBed) {
            this.TruckCoeficient = (float) (this.TruckCoeficient + 0.2);
        }
    }

    public float getTruckCoeficient() {
        return TruckCoeficient;
    }

    @Override
    public void displayDetails() {
        System.out.println("Truck Details:");
        System.out.println("Name: " + getName());
        System.out.println("Model: " + getModel());
        System.out.println("Registration Number: " + getRegistrationNumber());
        System.out.println("Type: " + getType());
        System.out.println("Manufacture Date: " + getDuration());
        System.out.println("Price: " + getPrice() + " DH");
        System.out.println("Has Cargo Bed: " + (HasCargoBed ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return super.toString() + ", hasCargoBed=" + HasCargoBed + "]";
    }
}
