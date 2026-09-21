package chapter02.lesson03;

public class Human {
    private String name;
    private int age;
    private boolean isMale;
    private static int money;

    public void print() {
        System.out.println("name=" + name);
        System.out.println("age=" + age);
        System.out.println("isMale=" + isMale);
    }

    public Human (String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        money += 10000;
    }
    public static int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
