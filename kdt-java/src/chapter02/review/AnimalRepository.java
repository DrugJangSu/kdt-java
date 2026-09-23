package chapter02.review;

import java.util.HashMap;
import java.util.Map;

public class AnimalRepository {
    private final Map<Long, Animal> store = new HashMap<>();
    private Long sequence = 0L;


    Animal save(Animal animal) {
        sequence += 1;
        animal.assignId(sequence);
        store.put(sequence, animal);
        return animal;
    }

    public Animal findById(Long id) {
        return store.get(id);
    }

}
