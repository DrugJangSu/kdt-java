package chapter02.lesson06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTitles {
    public static void main(String[] args) {

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("exam", "bring id"));
        posts.add(new Post("closed", "no class"));
        posts.add(new Post("quiz", "code"));

        posts.sort(new Comparator<Post>(){ // 무기명 클래스, 익명 클래스(Anonymous Class)

            @Override
            public int compare(Post first, Post second) {
                return first.getTitle().compareTo(second.getTitle());
            }
        });


    }
}
