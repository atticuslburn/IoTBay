package isd.group_4;

import java.util.Date;

public class Payment {
    private int paymentID;
    private String bankName;
    private String cardHolderName;
    private int cardNumber;
    private Date cardExpiryDate;
    private String cardCVV; //it should be deleted afterwards
    private boolean paymentStatus;
    private boolean paymentPending;

    public Payment() {

    }
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public int getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public Date getCardExpiryDate() {
        return cardExpiryDate;
    }
    public void setCardExpiryDate(Date cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }
    public String getCardCVV() {
        return cardCVV;
    }
    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }
    public boolean isPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public boolean isPaymentPending() {
        return paymentPending;
    }
    public void setPaymentPending(boolean paymentPending) {
        this.paymentPending = paymentPending;
    }
}
