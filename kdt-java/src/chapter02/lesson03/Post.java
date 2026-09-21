package chapter02.lesson03;

import java.util.Objects;

public class Post {

    private static int count = 0;
    // {접근제한자} {스태틱옵션} {final} {타입} 변수 = 1;
    private String title;
    private String body;
    private final String id;
    private boolean closed;

    public void print() {
        System.out.println("title= " + title);
        System.out.println("body= " + body);
    }

    public String getId() {
        return this.id;
    }

    public void changeTitle(String next) {
        if (this.closed) {
            System.out.println("reject=closed");
            return;
        }
        if (next == null || next.isEmpty()) {
            System.out.println("reject=empty-title");
            return;
        }
        this.title = next;
    }

    public void close() {
        this.closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    public Post(String title, String body) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("empty-title");
        }
        count = count + 1;
        this.id = "p" + count;
        this.title = title;
        this.body = body;
        this.closed = false;
    }

    public Post(String id, String title, String body) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("empty-title");
        }
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("empty-id");
        }
        this.id = id;
        this.title = title;
        this.body = body;
        this.closed = false;
        count = count + 1;
    }

    public static int getCount() {
        return count;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        Post that = (Post) o;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
// getter setter 자동완성 (vscode 기준 CMD + Shift + P / 인텔리제이 CMD + N)