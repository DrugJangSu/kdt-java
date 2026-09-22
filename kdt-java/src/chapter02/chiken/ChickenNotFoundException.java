package chapter02.chiken;

public class ChickenNotFoundException extends IllegalArgumentException {
    public ChickenNotFoundException(int id) {
        super("해당 ID의 치킨이 없습니다: " + id);



    }
}