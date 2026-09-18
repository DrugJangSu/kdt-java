import java.util.Scanner;

public class ParsePrice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        String raw = "4500";

//        System.out.println(raw + "1000");

//        int kimbap = Integer.parseInt( "4500");
//        int water = Integer.parseInt( "1000");
//        System.out.println(kimbap + water);

//        System.out.println("price?");
//        String raw = sc.nextLine();
//        int price = Integer.parseInt(raw);
//        System.out.println("price=" + price);
//
//        int price2 = Integer.parseInt(sc.nextLine());
//        System.out.println("price2=" + price2);


//        int total = 5500;
//        String text = String.valueOf(5500);

//        int kimbap = 4500;
//        double kimbap2 = kimbap;
//        System.out.println(kimbap2);

        // double -> int (소수를 정수로)
        double rating = 4.9;
        int stars = (int) rating;
        System.out.println(stars);

        // int -> double (정수를 소수로)
        int a = 30;
        double b = (double) a;
        System.out.println(b);



    }
}
