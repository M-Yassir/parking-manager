package com.example.demo;

public class Vehiclee {
    private String registrationNumber;
    private String name;
    private String model;
    private String type;
    private double time;
    private String subscription;
    private double price;
    private String fullName;
    private String nid;

    // Constructeur
    public Vehiclee(String registrationNumber, String name, String model, String type, double time, String subscription, double price, String fullName, String nid) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.model = model;
        this.type = type;
        this.time = time;
        this.subscription = subscription;
        this.price = price;
        this.fullName = fullName;
        this.nid = nid;
    }

    // Getters et Setters
    public String getRegistrationNumber() { return registrationNumber; }
    public String getName() { return name; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public double getTime() { return time; }
    public String getSubscription() { return subscription; }
    public double getPrice() { return price; }
    public String getFullName() { return fullName; }
    public String getNid() { return nid; }
}

