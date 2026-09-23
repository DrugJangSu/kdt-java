package chapter02.lesson06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseCollectionApp {
    public static void main(String[] args) {

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("p1", "closed", "no class"));
        posts.add(new Post("p2", "exam", "bring id"));

        System.out.println("size=" + posts.size());
        System.out.println("first=" + posts.get(0).getTitle());

//        Post post = new Post("p1", "closed", "no class");
//        post.print();
//        int a = 1;
//        String b = "123";
//        List<Integer> app = new ArrayList<>();
////        app.add(2);
////        app.get(11);
////        app.set(0, 3);
////        app.remove();
//        Set<Integer> app1 = new HashSet<>();
//        app.add(1);
//        app1.toArray();

    }
}