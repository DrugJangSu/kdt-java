package chapter02.lesson07;

import java.util.List;
import java.util.stream.Collectors;

public class StreamClosed {
    public static void main(String[] args) {
        List<Post> posts =BaseStreamApp.createPosts();

        posts.stream()
                .filter(post -> post.isClosed())
                .forEach(post -> System.out.println(post.getTitle()));

//        Long a = posts.stream()
        List<Post> a = posts.stream()
                .filter(post -> post.isClosed())
                .filter(post -> post.getTitle().contains("시험"))
                .toList();
//                .count();



        System.out.println(a);

    }
}
