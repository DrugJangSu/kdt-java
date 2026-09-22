package chapter02.chiken;

public class Chicken {
    private int id; // 치킨 고유 번호
    private String name; // 치킨 이름(후라이드, 양념, 프링클)
    private int price; // 가격(원)

    public Chicken(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
