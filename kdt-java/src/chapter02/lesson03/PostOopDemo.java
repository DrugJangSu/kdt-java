package chapter02.lesson03;

public class PostOopDemo {

    public static void main(String[] args) {
        Post post = new Post("closed", "no class");
        Post post1 = new Post("1231231231", "12312313");
        post.print();
        post1.print();

        System.out.println(Post.getCount());


        NoticePost noticePost = new NoticePost("t1", "t2", "t3");
        noticePost.print();
//        post.title = "closed";
//        post.setTitle("closed");
//        post.body = "no class";
//        post.setBody("no class");
    }
}
