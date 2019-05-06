package ro.msg.learning.shop.exception;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Long id){
        super("Could not found supplier with id: " + id);
    }
}
