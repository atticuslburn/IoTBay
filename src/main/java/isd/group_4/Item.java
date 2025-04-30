package isd.group_4;

import java.io.Serializable;

public class Item implements Serializable {
    private int itemID;
    private String itemName;
    private String itemDescription;
    private int quantity;
    private double price;

    public Item(int itemID, String itemName, String itemDescription, int quantity, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.quantity = quantity;
        this.price = price;
    }
    public int getItemID() {
        return itemID;
    }
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemDescription() {
        return itemDescription;
    }
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable(){
        return this.quantity > 0;
    }

    public void updateStock(int quantity){
        this.quantity = quantity;
    }

}
