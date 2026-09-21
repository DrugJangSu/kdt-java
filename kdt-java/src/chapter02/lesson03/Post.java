package chapter02.lesson03;

public class Post {

    private static int count = 0;

    private String title;
    private String body;

    public void print(){
        System.out.println("title=" + title);
        System.out.println("body=" + body);
    }
    // getter setter 자동완성 (vscode 기준 CMD + Shift + P / 인텔리제이 CMD + N)
    public Post(String newTitle, String newBody) {
        this.title = newTitle;
        this.body = newBody;
        count = count +1;
    }

    public static int getCount() {
        return count;
    }


    public Post(int newTitle, String newBody) {}


    public void setTitle(String title) {
        this.title = title;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }

}