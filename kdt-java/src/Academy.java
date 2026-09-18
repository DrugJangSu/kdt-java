import java.util.ArrayList;
import java.util.HashMap;

public class Academy {
    ArrayList<Student> list = new ArrayList<>(); // 전체 순회(printAll 등)에 사용할 리스트

    HashMap<Integer, Student> map = new HashMap<>(); // 번호로 빠르게 찾기 (findByNo 등) 용도

    public void addStudent(int no, String name, String subject, int score) {
        Student s = new Student();
        s.setInfo(no, name, subject, score);

        list.add(s);
        map.put(no, s);

        System.out.println("등록 완료: " + name);

    }
    public void printAll() {
        for (Student s : list) {
            s.printInfo();
        }
    }

    public void printOne(int no) {
        Student s = map.get(no);
        if (s == null) {
            System.out.println("없는 번호: " + no);
            return;
        }
        s.printInfo();
    }

    public void updateScore(int no, int newScore) {
        Student s = map.get(no);
        if (s == null) {
            System.out.println("없는 번호: " + no);
            return;
        }
        if (newScore < 0 || newScore > 100) {
            System.out.println("유효하지 않은 입력입니다. 점수는 0~100 사이여야 합니다. " + newScore);
            return;
        }
        s.setInfo(s.no, s.name, s.subject, newScore);
        s.score = newScore;
    }
    public void deleteStudent(int no) {
        Student s = map.remove(no);

        if (s == null) {
            System.out.println("없는 번호: " + no);
            return;
        }
        list.remove(s);
        System.out.println("삭제 완료 " + no);
    }
    ArrayList<Student> findAllBySubject(String subject) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : list) {
            if (s.subject.equals(subject)) {
                result.add(s);
            }
        }

        return result;
    }

    public void printBySubject(String subject) {

        ArrayList<Student> result = findAllBySubject(subject);

        if (result.isEmpty()) {
            System.out.println("해당 과목 수강생이 없습니다: " + subject);
            return;
        }
        System.out.println("==" + subject + " 수강생 ==");
        for (Student s : result) {
            s.printInfo();
        }



    }


    public static void main(String[] args) {
        Academy academy = new Academy();

        // 1. 학생 4명 등록
        academy.addStudent(1, "홍길동", "Java", 90);
        academy.addStudent(2, "김철수", "Java", 85);
        academy.addStudent(3, "이영희", "Python", 95);
        academy.addStudent(4, "박민수", "Java", 70);

        // 2. 전체 조회
        System.out.println("\n--- 전체 조회 ---");
        academy.printAll();

        // 3. 2번 점수 수정 후 전체 조회
        System.out.println("\n--- 2번 점수 100으로 수정 ---");
        academy.updateScore(2, 100);
        academy.printAll();

        // 4. 1번 삭제 후 전체 조회
        System.out.println("\n--- 1번 삭제 ---");
        academy.deleteStudent(1);
        academy.printAll();

        // 5. 3번 단건 조회
        System.out.println("\n--- 3번 단건 조회 ---");
        academy.printOne(3);

        // 6. 과목별 조회 (없는 과목 포함)
        System.out.println("\n--- 과목별 조회 ---");
        academy.printBySubject("Java");
        academy.printBySubject("Python");
        academy.printBySubject("C++"); // 존재하지 않는 과목
    }


}

