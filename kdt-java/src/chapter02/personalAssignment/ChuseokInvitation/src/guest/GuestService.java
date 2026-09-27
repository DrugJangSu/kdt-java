package guest;

import java.util.ArrayList;

public class GuestService {
    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public Guest register(String name) {
        if ((name == null) || name.equals("")) {
            throw new IllegalArgumentException("이름이 비어 있습니다.");
        }
        int id = repository.nextId();
        Guest guest = new Guest(id, name);
        repository.save(guest);
        return guest;
    }

    public Guest findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<Guest> findAll() {
        return repository.findAll();
    }

}
