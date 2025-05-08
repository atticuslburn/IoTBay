package isd.group_4;

import java.io.Serializable;
import java.util.Calendar;

public class Order implements Serializable {
    private int orderID;
    private Calendar orderDate;
    private int orderQuantity;
    private int itemID;
    private int userID;

    public Order(int orderID, Calendar orderDate, int orderQuantity, int userID,int itemID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.itemID = itemID;
        this.userID = userID;
        this.orderQuantity = orderQuantity;
    }
    public Order (int orderID, int orderQuantity, int userID, int itemID) {
        this.orderID = orderID;
        this.orderQuantity = orderQuantity;
        this.userID = userID;
        this.itemID = itemID;
        this.orderDate = Calendar.getInstance();
    }

    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public Calendar getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public int getUserID() {return userID; }
    public void setUserID(int userID) {this.userID = userID; }
    public int getOrderQuantity() {return orderQuantity;}
    public void setOrderQuantity(int orderQuantity) {this.orderQuantity = orderQuantity;}



}
