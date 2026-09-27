package songpyeon;

import java.util.ArrayList;

public class SongpyeonService {
    private final SongpyeonRepository repository;

    public SongpyeonService (SongpyeonRepository repository) {
        this.repository = repository;
    }
    public Songpyeon register(String name, String makerName, int styleChoice) {
        if (name == null || name.equals("") || makerName == null || makerName.equals("")) {
            throw new IllegalArgumentException("송편 이름과 빚은 사람 이름이 필요합니다.");
        }
        SongpyeonStylePolicy policy;
        if (styleChoice == 1) {
            policy = new PrettyStylePolicy();
        } else if (styleChoice == 2) {
            policy = new QuickStylePolicy();
        } else {
            throw new IllegalArgumentException("스타일은 1 또는 2입니다.");
        }
        int id = repository.nextId();
        Songpyeon songpyeon = new Songpyeon(id, name, makerName, policy.label(), policy.pieceCount(), policy.shapeScore());
        repository.save(songpyeon);
        return songpyeon;
    }
    public Songpyeon findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<Songpyeon> findAll() {
        return repository.findAll();
    }
    
}
