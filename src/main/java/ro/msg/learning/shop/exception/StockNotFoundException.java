package ro.msg.learning.shop.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Long id) {
        super("Could not found supplier with id: " + id);
    }
}
