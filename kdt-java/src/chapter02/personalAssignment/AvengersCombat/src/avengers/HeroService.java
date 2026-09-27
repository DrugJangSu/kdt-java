package avengers;

import java.util.ArrayList;

public class HeroService {
    private final HeroRepository repository;

    public HeroService(HeroRepository repository) {
        this.repository = repository;
    }

    public Hero register(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("히어로 이름이 비어 있습니다.");
        }
        int id = repository.nextId();
        Hero hero = new Hero(id, name);
        repository.save(hero);
        return hero;
    }

    public Hero findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<Hero> findAll() {
        return repository.findAll();
    }
}
