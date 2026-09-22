package chapter02.lesson05;

public class MemoController {
        private final PostService service;
        public MemoController(PostService service) {
                this.service = service;
        }
        public void createAndShow(String title, String body) {
                service.register(title, body); // 등록한다 -> 입력받은 타이틀과 바디를 통해서...
                service.printAll();
        }

}
