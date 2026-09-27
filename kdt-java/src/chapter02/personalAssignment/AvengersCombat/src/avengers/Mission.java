package avengers;

public class Mission {
    private final int id;
    private final String title;
    private final String heroName;


    public Mission(int id, String title, String heroName) {
        this.id = id;
        this.title = title;
        this.heroName = heroName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHeroName() {
        return heroName;
    }
}
