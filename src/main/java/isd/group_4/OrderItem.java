package isd.group_4;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int itemID;
    private int orderQuantity;

    public OrderItem(int itemID, int orderQuantity) {
        this.itemID = itemID;
        this.orderQuantity = orderQuantity;
    }

    public OrderItem() {
    }

    static public OrderItem createOrderItem(int itemID, int orderQuantity) {
        return new OrderItem(itemID, orderQuantity);
    }

    static public OrderItem startOrderItem() {
        return new OrderItem();
    }




    public int getItemID() {return itemID;}
    public int getOrderQuantity() {return orderQuantity;}
    public void setItemID(int itemID) {this.itemID = itemID;}
    public void setOrderQuantity(int orderQuantity) {this.orderQuantity = orderQuantity;}
}
