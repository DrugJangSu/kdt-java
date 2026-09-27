package avengers;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MissionRepository {
    private final HashMap<Integer, Mission> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber++;
        return id;
    }

    public void save(Mission mission) {
        store.put(mission.getId(), mission);
        ids.add(mission.getId());
    }

    public void replace(Mission mission) {
        findById(mission.getId());
        store.put(mission.getId(), mission);
    }

    public Mission findById(int id) {
        Mission found = store.get(id);
        if (found == null) {
            throw new HeroNotFoundException("미션을 찾을 수 없습니다. 번호=" + id);
        }
        return found;
    }


    public ArrayList<Mission> findAll() {
        ArrayList<Mission> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }
}
