package chapter02.lesson03;

public class NoticePost extends Post{ // Post가 부모, NoticePost가 자식

    Author author;

    private String place;

    public NoticePost(String title, String body, String place) {
        super(title, body);
        this.place = place;
    }

    public void print() {
        System.out.println("place=" + place);
        super.print();
    }

    public void print(int a) {
        System.out.println(a);

    }

}
