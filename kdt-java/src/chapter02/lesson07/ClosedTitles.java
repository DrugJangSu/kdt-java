package chapter02.lesson07;

import java.util.List;

public class ClosedTitles {

    public static void main(String[] args) {
        List<Post> posts = BaseStreamApp.createPosts();

         posts.stream()
                .filter(post -> post.isClosed())
//                .filter(Post::isClosed)
                .map(post -> post.getTitle())
//                .map(Post::getTitle)
                .toList();
    }
}
