import java.util.Scanner;

public class NoticeCard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("title?");
        String title = input.nextLine();
        System.out.println("body?");
        String body = input.nextLine();
        System.out.println("saved title=" + title);
        System.out.println("saved body=" + body);
        System.out.println("card=" + title + " " + "/" + " " + body);


    }
}
