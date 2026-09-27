package guest;

import java.util.ArrayList;

public class GuestRepository {
    private final ArrayList<Guest> guests = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber = nextNumber + 1;
        return id;
    }

    public void save(Guest guest) {
        guests.add(guest);
    }

    public Guest findById(int id) {
        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            if (guest.getId() == id) {
                return guest;
            }
        }
        throw new IllegalArgumentException("명단에 없습니다. 번호=" + id);
    }
    public ArrayList<Guest> findAll() {
        ArrayList<Guest> result = new ArrayList<>();
        for (int i = 0; i < guests.size(); i++) {
            result.add(guests.get(i));
        }
        return result;
    }

}
