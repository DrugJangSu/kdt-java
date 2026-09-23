package chapter02.review;

public class CatSoundPolicy implements SoundPolicy{
    @Override
    public String makeSound(String name) {
        if (name == null || name.equals("")) {
            return "냐옹";
        }
        return "야옹";
    }
}
