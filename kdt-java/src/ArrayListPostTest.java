import java.util.ArrayList;

public class ArrayListPostTest {
    // 어레이리스트를 만드는데 타입은 Post만 받을 수 있다.
    // 그리고 새 포스트 객체 3개를 만들어 해당 어레이리스트에 추가한다.
    // 반복문으로 그 3가지 각 객체에 존재하는 필드 2가지를 출력한다.

    public static void main(String[] args) {
        ArrayList<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        post1.title = "a";
        post1.body = "b";

        Post post2 = new Post();
        post2.title = "c";
        post2.body = "d";

        Post post3 = new Post();
        post3.title = "e";
        post3.body = "f";

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        /// 방법1
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);

//            System.out.println(posts.get(i)); // 이걸로 하면 안됨. 주소가 나오기 때문
            System.out.println(posts.get(i).title);
            System.out.println(posts.get(i).body);

        }
        /// 방법2
        for(Post p : posts) {
            System.out.println(p.title);
            System.out.println(p.body);
        }

    }
}