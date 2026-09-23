package chapter02.lesson07;

import java.util.List;

public class StreamSearch {
    public static void main(String[] args) {
        List<Post> posts = BaseStreamApp.createPosts();

        boolean hasExam = posts.stream()
                .anyMatch(post -> post.getTitle().contains("시험")); // 종단

        long closedCount = posts.stream()
                .filter(Post::isClosed)
                .count();

        String firstDinner = posts.stream()
                .filter(post -> post.getTitle().contains("저녁"))
                .map(post -> post.getTitle())
                .findFirst()
                .orElse("저녁 모임이 없다.");


        System.out.println(hasExam);
        System.out.println(closedCount);
        System.out.println(firstDinner);

    }
}
