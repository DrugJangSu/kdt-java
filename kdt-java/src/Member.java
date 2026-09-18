public class Member {

    // 문자열 id
    // 문자열 name
    // 정수 age
    String id;
    String name;
    int age;

    // <문제2>
    // 메서드 구현
    // 멤버 이름이 kim이라면 -> 출력을 "oh!" 아니라면 "oh2!"
    // 반환 타입 -> void
    // 메서드 이름 -> isNameKim()
    public void isNameKim() {
        if (this.name == "kim") {
            System.out.println("oh!");
        } else {
            System.out.println("oh2!");
        }
    }



}
