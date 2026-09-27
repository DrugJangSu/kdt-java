package grocery;

import java.util.ArrayList;
import java.util.HashMap;

public class GroceryRepository {
    private final HashMap<Integer, GroceryItem> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;


    public int nextId() {
        int id = nextNumber;
        nextNumber++;
        return id;
    }

    public void save(GroceryItem item) {
        store.put(item.getId(), item);
        ids.add(item.getId());
    }

    public GroceryItem findById(int id) {
        GroceryItem found = store.get(id);
        if (found == null) {
            throw new IllegalArgumentException("장을 찾을 수 없습니다. 번호=" + id);
        }
        return found;
    }

    public ArrayList<GroceryItem> findAll() {
        ArrayList<GroceryItem> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }
    public void replace(GroceryItem item) {
        findById(item.getId());
        store.put(item.getId(), item);
    }

    public void delete(int id) {
        findById(id);
        store.remove(id);
        ids.remove(Integer.valueOf(id));
    }

}
