package chapter02.lesson04;

public class NoticePost extends Post {
    private String place;

    public NoticePost(String title, String body, String place) {
        super(title, body);
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public void print() {
        System.out.println("place=" + place);
        super.print();
    }
}