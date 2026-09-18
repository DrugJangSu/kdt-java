public class TypeCheck {
    public static void main(String[] args) {

        int price = 4500; // 원 단위 정수
        double rating = 4.5; // 점이 있는 점수
        boolean soldOut = false; // true 또는 false만
        char seat = 'A'; // 글자 하나
        String menu = "kimbap"; // 글자 여러 개, 기본 타입 아님

        System.out.println("price=" + price);
        System.out.println("rating=" + rating);
        System.out.println("soldOut=" + soldOut);
        System.out.println("seat=" + seat);
        System.out.println("menu=" + menu);

        int kimbap = 4000;
        int water = 1000;
        System.out.println("wrong=" + kimbap + water); // 이어 붙임, 더하기 아님
        int total = kimbap + water; // 먼저 더함
        System.out.println("total=" + total); // 그다음 붙임
        System.out.println("wrong=" + kimbap + water);
    }
}
