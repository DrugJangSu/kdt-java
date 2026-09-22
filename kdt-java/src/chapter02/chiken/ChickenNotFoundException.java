package chapter02.chiken;

public class ChickenNotFoundException extends IllegalArgumentException {
    public ChickenNotFoundException(String message) {
        super(message);
    }
}
0