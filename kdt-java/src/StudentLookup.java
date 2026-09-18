
import java.util.HashMap;

public class StudentLookup {


    public static void main(String[] args) {

//        * 공통 규칙
//        키(key) : 학번 String
//        값(value) : 학생 이름 String
//        변수 이름 : students


        HashMap<String, String> students = new HashMap<>();
//
//        1단계 — put/get 기본 감각문제
//        students에 다음을 저장하세요.

//        "2024001" → "김민"
//        "2024002" → "이준"

//        다음을 출력하세요.
//        students.get("2024001")
//        students.get("2024002")
//        students.get("2024999")
/*
        <답안>
        students.put("2024001", "김민");
        students.put("2024002", "이준");

        System.out.println(students.get("2024001"));
        System.out.println(students.get("2024002"));
        System.out.println(students.get("2024999"));
*/
//        2단계 — 덮어쓰기와 size 문제
//        "2024001"에 "김민"을 put
//        "2024001"에 "김민수"를 다시 put
//        get("2024001")과 size() 출력
//        "2024002"에 "이준"을 put한 뒤 size() 다시 출력

//        <답안>
        students.put("2024001", "김민");

        System.out.println(students.get("2024001"));
        students.put("2024001", "김민수");

        System.out.println(students.get("2024001"));
        System.out.println(students.size());

        students.put("2024002", "이준");
        System.out.println(students.size());

//        3단계 — 이어서: containsKey로 안전 조회 문제
//        2단계 코드 아래에 이어서 작성하세요.
//        "2024001"이 있으면 이름 출력, 없으면 "missing"
//        "2024999"가 있으면 이름 출력, 없으면 "missing"
//        <답안>
        if (students.containsKey("2024001")) {
            System.out.println(students.get("2024001"));
        } else {
            System.out.println("missing");
        }
        if (students.containsKey("2024999")) {
            System.out.println(students.get("2024999"));
        } else {
            System.out.println("missing");
        }

//        4단계 — 이어서: keySet으로 전체 출력 문제
//        3단계 코드 아래에 이어서 작성하세요.
//        keySet()으로 모든 학번과 이름을 출력하세요.
//        <답안>
        for (String id : students.keySet()) {
            System.out.println(id);
            System.out.println(students.get(id));
        }


//        5단계 — 이어서: 추가 put과 중복 키 문제
//        4단계 코드 아래에 이어서 작성하세요.
//        "2024003" → "박소라" put
//        "2024001" → "김민" 다시 put (덮어쓰기)
//        출력:
//        get("2024001")
//        get("2024003")
//        size()
//        <답안>
        students.put("2024003", "박소라");
        students.put("2024001", "김민");
        System.out.println("--------------------");
        System.out.println(students.get("2024001"));
        System.out.println(students.get("2024003"));
        System.out.println(students.size());

//        6단계 — 이어서: 배열로 put, 중복 키
//        문제
//        5단계 코드 아래에 이어서 작성하세요.

//        String[] ids = {"2024004", "2024005", "2024004"};
//        String[] names = {"최윤", "정하", "최윤서"};

//        반복문으로 put한 뒤 출력:
//        size()
//        get("2024004")
//        get("2024005")
        System.out.println("--------------------");
        String[] ids = {"2024004", "2024005", "2024004"};
        String[] names = {"최윤", "정하", "최윤서"};

        for  (int i = 0; i < ids.length; i++) {
            students.put(ids[i], names[i]);
        }
        System.out.println(students.size());
        System.out.println(students.get("2024004"));
        System.out.println(students.get("2024005"));




        System.out.println("--------------------");



    }


}
