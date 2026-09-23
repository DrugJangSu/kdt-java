package chapter02.chicken;

public class Main {
    public static void main(String[] args) {
        Chicken c = new Chicken(1, "후라이드", 18000);
        System.out.println(c.getId() + " / " + c.getName() + " / " + c.getPrice());
    }
}
