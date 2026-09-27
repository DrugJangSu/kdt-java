package songpyeon;

import java.util.ArrayList;
import java.util.HashMap;

public class FamilyMemberRepository {
    private final HashMap<Integer, FamilyMember> store = new HashMap<>();
    private final ArrayList<Integer> ids = new ArrayList<>();
    private int nextNumber = 1;

    public int nextId() {
        int id = nextNumber;
        nextNumber++;
        return id;
    }

    public void save(FamilyMember member) {
        store.put(member.getId(), member);
        ids.add(member.getId());
    }

    public FamilyMember findById(int id) {
        FamilyMember found = store.get(id);
        if (found == null) {
            throw new SongpyeonNotFoundException("가족을 찾을 수 없습니다. 번호=" + id);
        }
        return found;
    }
    public ArrayList<FamilyMember> findAll() {
        ArrayList<FamilyMember> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(store.get(ids.get(i)));
        }
        return result;
    }

}


