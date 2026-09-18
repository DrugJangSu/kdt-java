import java.util.ArrayList;

public class MemberDemo {
    public static void main(String[] args) {
//        <문제1>
//        ArrayList<Member> list = new ArrayList<>(); 만들기
//        Member m = new Member(); 로 객체 하나 만들기
//        m.id = "m1"; m.name = "Kim"; m.age = 20; 넣기
//        list.add(m); 로 추가
//        for-each 로 전체 출력

        ArrayList<Member> list = new ArrayList<>();

        Member m1 = new Member();
        m1.id = "m1";
        m1.name = "Kim";
        m1.age = 20;
        list.add(m1);

        for (Member m : list) {
            System.out.println(m.id);
            System.out.println(m.name);
            System.out.println(m.age);
        }
        m1.isNameKim();

    }
}
