package chapter02.chiken;

public class CustomerNotFoundException extends IllegalArgumentException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
