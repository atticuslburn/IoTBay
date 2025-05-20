package isd.group_4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import isd.group_4.OrderItem;

public class Order implements Serializable {
    private int orderID;
    private Calendar orderDate;
    private int orderQuantity;
    private int itemID;
    private int userID;
    private List<OrderItem> orderItems;


    //constructors
    public Order(int orderID, Calendar orderDate, int userID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.userID = userID;
        this.orderItems = new ArrayList<OrderItem>();
    }
    public Order (int orderID, int userID) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = Calendar.getInstance();
        this.orderItems = new ArrayList<OrderItem>();
    }

    public Order (int userID) {
        this.orderID = -1;
        this.userID = userID;
        this.orderDate = Calendar.getInstance();
        this.orderItems = new ArrayList<OrderItem>();
    }

    public Order () {
        this.orderItems = new ArrayList<OrderItem>();
    }


    //methods
    static public Order startOrder(int userID) {
        return new Order(userID);
    }

    public int cartHasItem(int itemID) {
        // returns the index of the item
        ListIterator<OrderItem> orderItemIterator = orderItems.listIterator();
        while (orderItemIterator.hasNext()) {
            OrderItem orderItem = orderItemIterator.next();
            if (orderItem.getItemID() == itemID) {
                return orderItemIterator.previousIndex();
            }
        }
        return -1;

    }


    public void addItemToOrder(int itemID, int orderQuantity) {
        int orderItemIndex = cartHasItem(itemID);
        if (orderItemIndex != -1) {
            int orderItemQuantity = orderItems.get(orderItemIndex).getOrderQuantity();
            orderItems.get(orderItemIndex).setOrderQuantity(orderItemQuantity+orderQuantity);

        } else {
            this.addNewOrderItem(itemID, orderQuantity);
        }
    }

    public void editItemInOrder(int itemID, int orderQuantity) {

    }


    public void addNewOrderItem(int itemID, int orderQuantity) {
        OrderItem orderItem = new OrderItem(itemID, orderQuantity);
        orderItems.add(orderItem);
    }

    public void removeOrderItem(int itemID) {}

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
    public int getUserID() {return userID; }
    public void setUserID(int userID) {this.userID = userID; }
    public List<OrderItem> getOrderItems() {return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; } // shouldn't ever need to use this


    public List<Integer> getItemIDs() {
        List<Integer> itemIDs = new ArrayList<Integer>();
        for (OrderItem orderItem : orderItems) {
            itemIDs.add(orderItem.getItemID());
        }
        return itemIDs;
    }

    public int getAmountOfOrders() {
        return orderItems.size();
    }

    public void clearOrders() {
        orderItems.clear();
    }



}
