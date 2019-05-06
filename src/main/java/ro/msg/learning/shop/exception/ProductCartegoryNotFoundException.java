package ro.msg.learning.shop.exception;

public class ProductCartegoryNotFoundException extends RuntimeException {
    public ProductCartegoryNotFoundException(Long id) {
        super("Could not found product category with id: " + id);
    }
}
