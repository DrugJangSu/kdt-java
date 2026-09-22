package chapter02.chiken;

public class CustomerNotFoundException extends IllegalArgumentException {
    public CustomerNotFoundException(int id) {
        super("해당 ID의 고객이 없습니다: " + id);
    }
}
