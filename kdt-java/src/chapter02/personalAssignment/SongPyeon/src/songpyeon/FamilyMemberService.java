package songpyeon;

import java.util.ArrayList;

public class FamilyMemberService {

    private final FamilyMemberRepository repository;

    public FamilyMemberService(FamilyMemberRepository repository) {
        this.repository = repository;
    }

    public FamilyMember register(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("가족 이름이 비어 있습니다.");
        }
        int id = repository.nextId();
        FamilyMember member = new FamilyMember(id, name);
        repository.save(member);
        return member;
    }

    public FamilyMember findById(int id) {
        return repository.findById(id);
    }

    public ArrayList<FamilyMember> findAll() {
        return repository.findAll();
    }

}
