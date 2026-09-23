package chapter02.lesson07;

import java.util.List;
import java.util.function.Predicate;

public class LambdaClosed {
    public static void main(String[] args) {
        List<Post> posts = BaseStreamApp.createPosts();

        Predicate<Post> isClosed = post -> post.isClosed();

        for (Post post : posts) {
            if (isClosed.test(post)) {
                System.out.println(post.getTitle());
            }
        }


        }

    public static boolean isClosed(Post post) {
        return post.isClosed();

    }
}
