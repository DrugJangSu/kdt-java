import java.util.Scanner;

public class NameTag {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("name?");
        String name = input.nextLine();
        System.out.println("hello=" + name);
    }
}
