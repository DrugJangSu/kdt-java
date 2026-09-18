import java.util.Scanner;

public class OrderCard {
    public static void main(String[] args) {
        int kimbap = 4500;
        int water = 1000;
        int total = kimbap + water;
        Scanner input = new Scanner(System.in);
        System.out.println("nick?");
        String nick = input.nextLine();
        System.out.println("menu?");
        String menu = input.nextLine();
        System.out.println("note?");
        String note = input.nextLine();
        System.out.println("nick=" + nick);
        System.out.println("menu=" + menu);
        System.out.println("note=" + note);
        System.out.println("kimbap=" + kimbap);
        System.out.println("water=" + water);
        System.out.println("total=" + total);
        System.out.println("slip=" + nick + "/" + menu + "/" + total);

    }
}

