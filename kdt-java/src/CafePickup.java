import java.util.Scanner;

public class CafePickup {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("nickname?");
        String line1 = input.nextLine();
        System.out.println("menu?");
        String line2 = input.nextLine();
        System.out.println("place?");
        String line3 = input.nextLine();
        System.out.println("pickup=" + line1 + " " + line2 + " " + line3);

    }
}
