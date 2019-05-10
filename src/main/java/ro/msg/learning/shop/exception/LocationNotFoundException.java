package ro.msg.learning.shop.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(Integer id) {
        super("Could not found location with id: " + id);
    }

}
