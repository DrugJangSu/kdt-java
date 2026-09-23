package chapter02.review;

public class DogSoundPolicy implements SoundPolicy{
    @Override
    public String makeSound(String name) {
        if (name == null || name.equals("")) {
            return "왈왈";
        }
        return "멍멍";
    }
}
