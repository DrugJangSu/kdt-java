package chapter02.chiken;

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
        Chicken chicken = store.get(id);
        if (chicken == null) {
            throw new ChickenNotFoundException(id);
        }
        return chicken;
    }
    public List<Chicken> findAll() {
        return new ArrayList<>(store.values());
    }
}
