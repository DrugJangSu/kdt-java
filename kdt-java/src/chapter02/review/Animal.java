package chapter02.review;

public class Animal {
    private Long id;
    private final String name; // 동물 이름
    private final String sound; // 울음소리

    public Animal(String name, String sound) {
        this.name = name;
        this.sound = sound;
    }

    public void assignId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSound() {
        return sound;
    }
}
