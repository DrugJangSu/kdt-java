package avengers;

import java.util.ArrayList;
import java.util.HashMap;

public class HeroRepository {
    private final HashMap<Integer, Hero> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id =nextNumber;
        nextNumber++;
        return id;
    }

    public void save(Hero hero) {
        store.put(hero.getId(), hero);
        ids.add(hero.getId());
    }

    public Hero findById(int id) {
        Hero found = store.get(id);
        if (found == null) {
            throw new HeroNotFoundException("히어로를 찾을 수 없습니다. 번호=" + id);
        }
        return found;
    }

    public ArrayList<Hero> findAll() {
        ArrayList<Hero> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }

}
