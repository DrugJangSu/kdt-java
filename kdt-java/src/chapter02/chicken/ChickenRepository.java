package chapter02.chicken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChickenRepository {
    private final Map<Integer, Chicken> store = new HashMap<>();

    public void save(Chicken chicken) {
        store.put(chicken.getId(), chicken);
    }
    public Chicken findById(int id) {
        Chicken found = store.get(id);
        if (found == null) {
            throw new ChickenNotFoundException(id);
        }
        return found;
    }
    public List<Chicken> findAll() {
        return new ArrayList<>(store.values());
    }
}
