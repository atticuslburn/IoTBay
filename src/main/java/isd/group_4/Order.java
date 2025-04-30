package isd.group_4;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int orderID;
    private Date orderDate;
    private int itemID;

    public Order(int orderID, Date orderDate, int itemID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.itemID = itemID;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

}
