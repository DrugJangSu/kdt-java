public class ChangeGate {
    public static void main(String[] args) {
//        boolean a = true;
        // == 같다.
//        System.out.println(500<0);
//        int charge = 500;
//
//        if (charge < 0) {
//            System.out.println("short!");
//        } else {
//            System.out.println("charge=" + charge);
//        }
//
//        int score = 76;
//        char grade;
//        if (score >= 90) {
//            grade = 'A';
//        } else if (score >= 80) {
//            grade = 'B';
//        } else if (score >= 70) {
//            grade = 'C';
//        } else {
//            grade = 'F';
//        }
//        System.out.println(grade);

//        int a = (int) true; // boolean cannot be converted to int
//      && : and
//      || : or

//        if (true || false) { // 이게 참이라도
//            if (1 > 0) { // 이게 거짓이면
//                System.out.println("good!"); // 이게 안돌아감.
//            }
//        }
        String a = "kimbap";
        String b = "kimbap";

        System.out.println(a == b);

        if (a.equals(b)) { // 값을 비교 vs a==b는 주소를 비교
            System.out.println("true!");
        } else {
            System.out.println("false!");
        }
        System.out.println(a == b);

    // a==b는 주소를 비교하는 것이고(객체비교)
    // .equals()는 값을 비교하는 것.(내용비교)
    // 예시
    /*
    한국공장에서 만든 현대차 = String a = new String("kimbap");
    미국공장에서 만든 현대차 = String b = new String("kimbap");
    a==b의 경우 -> 이 두 차는 같은 차인가? false.
        생긴 건 같지만 다른 공장(메모리)에서 만들어진 다른 차
    a.equals(b) -> 이 두 차의 스펙(값)은 같아? true.
        결론적으로 둘다 같은 현대차. 똑같은 스펙임.
    */
    }
}
