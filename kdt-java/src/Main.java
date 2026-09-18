public class Main {

    public static void main(String[] args) {
        StudentService service = new StudentService();

        // 1. 학생 4명 등록
        service.join(1, "홍길동", "Java", 90);
        service.join(2, "김철수", "Java", 85);
        service.join(3, "이영희", "Python", 95);
        service.join(4, "박민수", "Java", 70);

        // 2. 중복 번호 등록 시도 → "이미 존재하는 번호: 1" 출력되어야 함
        System.out.println("\n--- 중복 등록 시도 ---");
        service.join(1, "중복맨", "Java", 50);

        // 3. 전체 조회
        System.out.println("\n--- 전체 조회 ---");
        service.printAll();

        // 4. 2번 점수 수정 후 전체 조회
        System.out.println("\n--- 2번 점수 100으로 수정 ---");
        service.changeScore(2, 100);
        service.printAll();

        // 5. 1번 삭제 후 전체 조회
        System.out.println("\n--- 1번 삭제 ---");
        service.remove(1);
        service.printAll();

        // 6. 3번 단건 조회
        System.out.println("\n--- 3번 단건 조회 ---");
        service.printOne(3);

        // 7. 과목별 조회 (없는 과목 포함)
        System.out.println("\n--- 과목별 조회 ---");
        service.printBySubject("Java");
        service.printBySubject("Python");
        service.printBySubject("C++"); // 존재하지 않는 과목


    }
}
