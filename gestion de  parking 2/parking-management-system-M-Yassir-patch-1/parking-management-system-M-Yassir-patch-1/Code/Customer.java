package parking_managment_system.Code;

public class Customer {
    private String firstName;
    private String lastName;
    private String nid; // National ID

    // Default Constructor
    public Customer() {
        this.firstName = "Unknown";
        this.lastName = "Unknown";
        this.nid = "Unknown";
    }

    // Parameterized Constructor
    public Customer(String firstName, String lastName, String nid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nid = nid;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    // toString Method
    @Override
    public String toString() {
        return "Customer [FirstName=" + firstName + ", LastName=" + lastName + ", NID=" + nid + "]";
    }

}
