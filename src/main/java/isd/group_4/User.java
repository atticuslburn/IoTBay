package isd.group_4;

import isd.group_4.exceptions.InvalidEmailException;
import isd.group_4.exceptions.InvalidPhoneException;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String postcode;
    private String role;
    private boolean loggedInUser;

    public User(){
        userID = 0;
        email = "1";
        password = "2";
        role = "customer";
    }

    public User (String password, String email, String firstName, String lastName, String phone, String streetNumber, String streetName, String suburb, String postcode, String role) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.suburb = suburb;
        this.postcode = postcode;
        this.loggedInUser = false;
        this.role = role;
    }

    public boolean checkPassword(String password){
        return password.equals(this.password);
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public void setEmail (String email) throws InvalidEmailException {
        if (!validateEmail(email)) throw new InvalidEmailException("Invalid email: "+email);
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) throws InvalidPhoneException {
        if (!validPhone(phone)) {
            throw new InvalidPhoneException("Invalid phone number: " + phone);
        }
        this.phone = phone;
    }
    public String getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
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
    public void setPostcode(String postCode) {
        this.postcode = postcode;
    }
    public void login() {this.loggedInUser = true;}
    public void logout() {this.loggedInUser = false;}

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }

    boolean validateEmail(String email) {
        return email.contains("@");
    }

    boolean validPhone(String phone) {
        return phone.matches("\\d+");
    }




}
