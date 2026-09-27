package songpyeon;

public class FamilyMember {
    private final int id;
    private final String name;


    public FamilyMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
