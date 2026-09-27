package avengers;

import java.util.ArrayList;
import java.util.HashMap;

public class MissionLogRepository {
    private final HashMap<Integer, MissionLog> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber++;
        return id;
    }

    public void save(MissionLog log) {
        store.put(log.getId(), log);
        ids.add(log.getId());
    }

    public MissionLog findById(int id) {
        MissionLog found = store.get(id);
        if (found == null) {
            throw new HeroNotFoundException("로그를 찾을 수 없습니다. 번호=" + id);
        }
        return found;
    }

    public ArrayList<MissionLog> findAll() {
        ArrayList<MissionLog> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }
}
