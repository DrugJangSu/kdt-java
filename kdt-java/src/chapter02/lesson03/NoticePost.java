package chapter02.lesson03;

public class NoticePost extends Post{ // Post가 부모, NoticePost가 자식

    private String place;

    public NoticePost(String title, String body) {
        super(title, body);
    }
}
