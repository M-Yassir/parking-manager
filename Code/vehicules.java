import java.util.HashMap;

public abstract class Vehicle {
    // I made a properties class inside of the vehicle class because the 'string property' was limited
    private class Properties {
        float maxSpeed;
        boolean hasPassengerSeats; //4 or 2 seats
        boolean hasCargoBed; // is it rmoouuuuuuuuuuk

        public Properties(float maxSpeed, boolean hasPassengerSeats, boolean hasCargoBed) {
            this.maxSpeed = maxSpeed;
            this.hasPassengerSeats = hasPassengerSeats;
            this.hasCargoBed = hasCargoBed;
        }

        public float getMaxSpeed() {
            return maxSpeed;
        }

        public void setMaxSpeed(float maxSpeed) {
            this.maxSpeed = maxSpeed;
        }

        public boolean hasPassengerSeats() {
            return hasPassengerSeats;
        }

        public void setHasPassengerSeats(boolean hasPassengerSeats) {
            this.hasPassengerSeats = hasPassengerSeats;
        }

        public boolean hasCargoBed() {
            return hasCargoBed;
        }

        public void setHasCargoBed(boolean hasCargoBed) {
            this.hasCargoBed = hasCargoBed;
        }
    }

    protected String name;
    protected String model;
    protected String registrationNumber; // (matricule)
    protected String type;
    protected String date;
    protected int price;
    private Properties property; // Internal properties object
    protected HashMap<String, Integer> SubscriptionPlan = new HashMap<>();

    // Initialize subscription plans in a hash map
    {
        SubscriptionPlan.put("1 day", 15); // if it is one day you pay 15dh......
        SubscriptionPlan.put("3 days", 30);
        SubscriptionPlan.put("1 week", 60);
        SubscriptionPlan.put("2 weeks", 110);
        SubscriptionPlan.put("1 month", 200);
        SubscriptionPlan.put("2 months", 380);
        SubscriptionPlan.put("3 months", 550);
        SubscriptionPlan.put("6 months", 1000);
    }

    public Vehicle() {
        this.name = "Unknown";
        this.model = "Unknown";
        this.registrationNumber = "Unknown";
        this.type = "Unknown";
        this.date = "Unknown";
        this.price = 0;
        this.property = new Properties(0, false, false);
    }

    public Vehicle(String name, String model, String registrationNumber, String type, String date, int price, float maxSpeed, boolean hasPassengerSeats, boolean hasCargoBed) {
        this.name = name;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.date = date;
        this.price = price;
        this.property = new Properties(maxSpeed, hasPassengerSeats, hasCargoBed);
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getMaxSpeed() {
        return property.getMaxSpeed();
    }

    public void setMaxSpeed(float maxSpeed) {
        property.setMaxSpeed(maxSpeed);
    }

    public boolean hasPassengerSeats() {
        return property.hasPassengerSeats();
    }

    public void setHasPassengerSeats(boolean hasPassengerSeats) {
        property.setHasPassengerSeats(hasPassengerSeats);
    }

    public boolean hasCargoBed() {
        return property.hasCargoBed();
    }

    public void setHasCargoBed(boolean hasCargoBed) {
        property.setHasCargoBed(hasCargoBed);
    }

    public int getSubscriptionPrice(String plan) {
        return SubscriptionPlan.getOrDefault(plan, -1);
    }

    public abstract void displayDetails();

    @Override
    public String toString() {
        return "Vehicle [name=" + name + ", model=" + model + ", registrationNumber=" + registrationNumber
                + ", type=" + type + ", date=" + date + ", price=" + price + ", properties=" + property + "]";
    }
}
