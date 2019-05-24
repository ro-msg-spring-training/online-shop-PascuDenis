package ro.msg.learning.shop.exception;

public class FailedToCreateOrderProductException extends RuntimeException {
    public FailedToCreateOrderProductException() {
    }

    public FailedToCreateOrderProductException(String message) {
        super("Could not create order");
    }

}
