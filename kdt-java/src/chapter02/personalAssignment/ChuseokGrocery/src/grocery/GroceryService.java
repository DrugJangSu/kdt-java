package grocery;

import java.util.ArrayList;

public class GroceryService {
    private final GroceryRepository repository;


    public GroceryService(GroceryRepository repository) {
        this.repository = repository;
    }

    public GroceryItem register(String name, int quantity) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("이름이 비어 있습니다.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1 이상입니다.");
        }
        int id = repository.nextId();
        GroceryItem item = new GroceryItem(id, name, quantity);
        repository.save(item);
        return item;
    }

    public GroceryItem findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<GroceryItem> findAll() {
        return repository.findAll();
    }

    public GroceryItem changeQuantity(int id, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1 이상입니다.");
        }
        GroceryItem found = repository.findById(id);
        GroceryItem updated = new GroceryItem(found.getId(), found.getName(), quantity);
        repository.replace(updated);
        return updated;
    }

    public void delete(int id) {
        repository.delete(id);
    }

}
