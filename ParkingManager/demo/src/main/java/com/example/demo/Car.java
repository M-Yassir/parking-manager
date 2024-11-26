package com.example.demo;

public class Car extends Vehicle {
    private boolean HasFourDoors;
    private float CarCoeficient = 1;

    public Car() {
        super();
        this.HasFourDoors = false;
    }

    public Car(String name, String model, String registrationNumber, String type, int duration, boolean HasFourDoors) {
        super(name, model, registrationNumber, type, duration);
        this.HasFourDoors = HasFourDoors;
        this.price = (int) CarCoeficient*(3*this.duration);
    }

    public boolean isHasFourDoors() {
        return HasFourDoors;
    }



    public void setHasFourDoors(boolean HasFourDoors) {
        this.HasFourDoors = HasFourDoors;
    }

    public void setCarCoeficient(){
        if(HasFourDoors) {
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



    @Override
    public String toString() {
        return super.toString() + ", HasFourDoors=" + HasFourDoors + "]";
    }
}
