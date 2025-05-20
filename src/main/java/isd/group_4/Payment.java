package isd.group_4;

import java.util.Date;

public class Payment {
    private int paymentID;           // this will be the primary key
    private int orderID;
    private int userID;
    private int cardID;
    private String bankName;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private int cardCVV;
    private boolean paymentStatus;
    private int paymentAmount;
    private String paymentDate;

    // reformatted nima's code and and changes some type for some fields and added new fields
    public Payment(int orderID, int userID, int cardID, String bankName, String cardNumber,
                   String cardHolderName, String cardExpiryDate, int cardCVV,
                   boolean paymentStatus, int paymentAmount, String paymentDate) {
        this.orderID = orderID;
        this.userID = userID;
        this.cardID = cardID;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpiryDate = cardExpiryDate;
        this.cardCVV = cardCVV;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
    public Payment (int cardID, String bankName, String cardNumber,
                    String cardHolderName, String cardExpiryDate, int cardCVV,
                    boolean paymentStatus, int paymentAmount, String paymentDate) {
        this.cardID = cardID;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpiryDate = cardExpiryDate;
        this.cardCVV = cardCVV;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public Payment(int paymentID, int orderID, int userID, int cardID, String bankName, String cardNumber,
                   String cardHolderName, String cardExpiryDate, int cardCVV,
                   boolean paymentStatus, int paymentAmount, String paymentDate) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.userID = userID;
        this.cardID = cardID;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpiryDate = cardExpiryDate;
        this.cardCVV = cardCVV;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public Payment(int paymentID, int orderID, int userID, int cardID,
                   boolean paymentStatus, int paymentAmount, String paymentDate) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.userID = userID;
        this.cardID = cardID;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }






    // paymentID
    public int getPaymentID() { return paymentID; }
    public void setPaymentID(int paymentID) { this.paymentID = paymentID; }

    // orderID
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

    // userID
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    // cardTypeID
    public int getCardID() { return cardID; }
    public void setCardID(int cardID) { this.cardID = cardID; }

    // bankName
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    // cardNumber
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    // cardHolderName
    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    // cardExpiryDate
    public String getCardExpiryDate() { return cardExpiryDate; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }

    // cardCVV
    public int getCardCVV() { return cardCVV; }
    public void setCardCVV(int cardCVV) { this.cardCVV = cardCVV; }

    // paymentStatus
    public boolean isPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(boolean paymentStatus) { this.paymentStatus = paymentStatus; }

    // paymentAmount
    public int getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(int paymentAmount) { this.paymentAmount = paymentAmount; }

    // paymentDate
    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
}
