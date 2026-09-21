package chapter02.lesson04;

public class Post {
    private String title;
    private String body;
    private static int count = 0;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
        count = count + 1;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public static int getCount() {
        return count;
    }

    public void print() {
        System.out.println("title=" + title);
        System.out.println("body=" + body);
    }
}