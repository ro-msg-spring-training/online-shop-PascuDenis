package ro.msg.learning.shop.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Integer id) {
        super("Could not found supplier with id: " + id);
    }
}
