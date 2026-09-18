
public class PostDemo {

    public static void main(String[] args) {
        Post post = new Post();
        post.body = "본문1";
        post.title = "제목1";

        Post post1 = new Post();
        post1.body = "본문2";
        post1.title = "제목2";
//        Post post3 = new Post();
//        System.out.println(post3.title); // 제목이 없어서 null 리턴

//        System.out.println(post1.body);

        System.out.println(post.plusTitle());
        System.out.println(post.plusBody());


        post.printArg("good!"); // 파라미터 parameter(함수에 넘겨주는 값)
        post.addBody("lol");

        post.print(); // 함수(메서드) 호출

        post.addTitle("1", "2"); //
        post.addTitle("1", 2); //

        post.test("good!");

    }
}
