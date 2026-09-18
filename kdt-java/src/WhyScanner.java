import java.util.Scanner;

public class WhyScanner {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        System.out.println("Ready!"); // 출력1
        String line = input.nextLine(); // 입력1
        System.out.println("Ready!"); // 출력2
        System.out.println("menu:" + line); // 출력3
        String line2 = input.nextLine(); // 입력2
        System.out.println("menu:" + line2); // 출력4

    }
}
