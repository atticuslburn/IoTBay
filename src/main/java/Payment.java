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
}
