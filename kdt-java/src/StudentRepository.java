import java.util.ArrayList;
import java.util.HashMap;

public class StudentRepository {
    ArrayList<Student> list = new ArrayList<>();
    HashMap<Integer, Student> map = new HashMap<>();

    void save(Student s) {
        list.add(s);
        map.put(s.no, s);
    }

    ArrayList<Student> findAll() {
        return list;
    }

    Student findByNo (int no) {
        return map.get(no);
    }

    void updateScore(Student s, int newScore) {
        s.score = newScore;
    }
    void delete(int no) {
        Student s = map.remove(no);
        if (s != null) {
            list.remove(s);
        }
    }

    public ArrayList<Student> findAllBySubject(String subject) {
        ArrayList<Student> result = new ArrayList<>();

        for (Student s : list) {
            result.add(s);
        }

        return result;
    }
}
