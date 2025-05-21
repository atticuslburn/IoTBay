package isd.group_4;

//edit and extend from "User"



public class Customer {
    private int id;
    private String name;
    private String email;
    private String type;      // "individual" or "company"
    private String address;
    private boolean active;   // true = active, false = deactivated

    public Customer() { }

    public Customer(int id, String name, String email,
                    String type, String address, boolean active) {
        this.id      = id;
        this.name    = name;
        this.email   = email;
        this.type    = type;
        this.address = address;
        this.active  = active;
    }

    // Getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
