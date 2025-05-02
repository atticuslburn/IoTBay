package isd.group_4.exceptions;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }

    public InvalidPhoneException() {
    super("Invalid phone number.");
    }
}
