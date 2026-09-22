package chapter02.chiken;

public class InvalidGradeException extends IllegalArgumentException {
    public InvalidGradeException(String message) {
        super(message);
    }
}
