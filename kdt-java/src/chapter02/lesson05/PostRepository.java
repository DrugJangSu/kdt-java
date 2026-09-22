package chapter02.lesson05;

import java.util.List;

public interface PostRepository {

    void save(Post post);
    List<Post> findAll();

}