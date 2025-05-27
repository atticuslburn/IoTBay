package isd.group_4;


import java.io.Serializable;

public class Card implements Serializable {
    private int cardID;
    private int userID;
    private int cardTypeID;
    private String bankName;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;

    public Card(){
    }

    public int getCardID() {
        return cardID;
    }
    public void setCardID(int cardID) {this.cardID = cardID;}
    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    public int getCardTypeID() {return cardTypeID;}
    public void setCardTypeID(int cardTypeID) {this.cardTypeID = cardTypeID;}
    public String getBankName() {return bankName;}
    public void setBankName(String bankName) {this.bankName = bankName;}
    public String getCardNumber() {return cardNumber;}
    public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}
    public String getCardHolderName() {return cardHolderName;}
    public void setCardHolderName(String cardHolderName) {this.cardHolderName = cardHolderName;}
    public String getCardExpiryDate() {return cardExpiryDate;}
    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }
}







