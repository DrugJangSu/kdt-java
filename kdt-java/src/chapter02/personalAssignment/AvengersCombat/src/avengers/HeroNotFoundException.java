package avengers;

public class HeroNotFoundException extends IllegalArgumentException {
    public HeroNotFoundException(String message) {
        super(message);
    }
}
