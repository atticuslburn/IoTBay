import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int streetNumber;
    private String suburb;
    private String postcode;

    public User(){}

    public User (int userID, String firstName, String lastName, String email, String phone, int streetNumber, String suburb, String postcode) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.streetNumber = streetNumber;
        this.suburb = suburb;
        this.postcode = postcode;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    public String getSuburb() {
        return suburb;
    }
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
