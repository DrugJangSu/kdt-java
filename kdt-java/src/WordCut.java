import java.util.Scanner;

public class WordCut {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("menu?");
        String word = input.next();
        String rest = input.nextLine();
        System.out.println("word=" + word);
        System.out.println("rest=" + rest);
    }
}
