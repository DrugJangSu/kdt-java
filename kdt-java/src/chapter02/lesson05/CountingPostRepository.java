package chapter02.lesson05;

import java.util.ArrayList;
import java.util.List;

public class CountingPostRepository implements PostRepository {
    private int saveCount;
    private final List<Post> posts = new ArrayList<>();

    @Override
    public void save(Post post) {
        saveCount++;
        posts.add(post);
    }

    @Override
    public List<Post> findAll() { return new ArrayList<>(posts); }
    public int getSaveCount() { return saveCount; }
}