package chapter02.chiken;

public class Customer {
    private final int id; // 고객 고유 번호
    private final String name; // 고객 이름
    private final String grade; // 등급(NORMAL VIP NEWBIE)

    public Customer(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }
}

