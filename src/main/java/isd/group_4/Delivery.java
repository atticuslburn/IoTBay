package isd.group_4;

import java.io.Serializable;
import java.util.Date;

public class Delivery implements Serializable {
    private int deliveryID;
    private Date deliveryDate;
    private int orderID;
    private String deliveryReciever;

    public Delivery(int deliveryID, Date deliveryDate, int orderID, String deliveryReciever) {
        this.deliveryID = deliveryID;
        this.deliveryDate = deliveryDate;
        this.orderID = orderID;
        this.deliveryReciever = deliveryReciever;
    }

    public int getDeliveryID() {
        return deliveryID;
    }
    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public String getDeliveryReciever() {
        return deliveryReciever;
    }
    public void setDeliveryReciever(String deliveryReciever) {
        this.deliveryReciever = deliveryReciever;
    }
}
