package avengers;

import java.util.ArrayList;

public class MissionService {
    private final MissionRepository repository;

    public MissionService(MissionRepository repository) {
        this.repository = repository;
    }

    public Mission register(String title) {
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("미션 이름이 비어 있습니다.");
        }
        int id = repository.nextId();
        Mission mission = new Mission(id, title, "");
        repository.save(mission);
        return mission;
    }

    public Mission assign(int id, String heroName) {
        if (heroName == null || heroName.equals("")) {
            throw new IllegalArgumentException("배정할 히어로 이름이 없습니다.");
        }
        Mission found = repository.findById(id);
        Mission updated = new Mission(found.getId(), found.getTitle(), heroName);
        repository.replace(updated);
        return updated;
    }

    public Mission findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<Mission> findAll() {
        return repository.findAll();
    }
}
