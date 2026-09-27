package avengers;

import java.util.ArrayList;

public class MissionLogService {
    private final MissionLogRepository repository;

    public MissionLogService(MissionLogRepository repository) {
        this.repository = repository;
    }

    public MissionLog record(String missionTitle, String heroName, String strategyLabel, int damage, int teamScore) {
        if (missionTitle == null || missionTitle.equals("") || heroName == null || heroName.equals("")) {
            throw new IllegalArgumentException("미션과 히어로 이름이 필요합니다.");
        }
        int id = repository.nextId();
        MissionLog log = new MissionLog(id, missionTitle, heroName, strategyLabel, damage, teamScore);
        repository.save(log);
        return log;
    }

    public MissionLog findById(int id) {
        return repository.findById(id);
    }
    public ArrayList<MissionLog> findAll() {
        return repository.findAll();
    }
}
