// 설계도
public class Post {
    String title; // 제목 필드
    String body; // 본문 필드

    public void print() {
        System.out.println(title);
        System.out.println(body);
        // void는 반환할 타입이 없어서 Return 불필요.
    }

    public String showTitle() {
        return "okay";
        // 반환할 타입이 String이라서 Return문을 반드시 써야 되고 그 뒤의 값은 타입이 맞아야 함.
    }


// 즉흥문제1
//    함수이름은 plusTitle 이고 반환값은 문자열이다. 반환할 값은 title 필드 값에 있는 문자열 더하기 "1"을 해주면된다.
    public String plusTitle() {
        return title + "1";
    }

// 즉흥문제2
//  함수이름은 isBody이고 반환타입은 boolean, 반환할 값은 만약에 body값이 null 이라면 true를 반환하고
//  아니면 false를 반환
//    public boolean isBody() {
//        if (body == null) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public String plusBody() {
        return body + "1";
    }

    // 모범답안
    public boolean isBody() {
        if (body == null) {
            return true; // early return이라고 함. return을 만나는 순간 함수가 그 자리에서 즉시 종료되기 때문에, else를 사용 안해도 무방함
        }
            return false;
    }

    // 아규먼트(Argument)를 받는 메서드
    public void printArg(String arg) { // 문자열 타입의 한개의 인자를 받고 본문에서 출력함
        System.out.println(arg);
    }

    // 아규먼트 (String arg)를 받는 메서드 addBody를 만들고 반환타입은 없음.
    // 메서드 결과는 화면에 body + 넘겨받은 arg를 출력.
    public void addBody(String arg) {
        System.out.println(body + arg);
    }

    // 인자를 반드시 두개 넣어야 함.
    public void addTitle(String arg, String arg2) {
        System.out.println(title + arg + arg2);
    }

    // 함수명이 똑같아도 인자에 있는 타입들이 다르면 정상적으로 실행됨.(위와 동일함에도 불구하고 오류X, 알맞은 인자를 넣은 메서드가 실행됨)
    public void addTitle(String arg, int arg2) {
        System.out.println(title + arg + arg2);
    }

    public void test(String arg, int... ints) { // 가변인자 : int... 인 경우는 안받을 수도 있다는 뜻. 대신 반드시 앞의 것은 필수로 넣어줘야 함.

    }

    //    public void test(String... arg2, String arg3) <== 에러. 이렇게 하면 안됨
    // 가변인자 위치는 항상 뒤, 맨 끝에 위치시킬 것. 그리고 꼭 필요한 인자들은 맨 앞에 위치시키기 (오류방지)
    // 가변인자는 딱 하나만 할 수 있음.
//    public void test2(Object... arg) { // 거의 사용하지는 않음. 여기서 Object는 사실상 모든 상위개념, 모든 객체 가능
//        for(Object a: arg) {
//
//        }
//    }
// Post라는 걸 만들 때 -> 하위 필드는 행위가 절대 없음. 자원이자 데이터를 넣는 용도임.
// 하지만 행위로서 필드를 접근하는 게 무조건 필수임.

// public void print() 는 행위.
// void는 반환할 타입이 없어서 return문을 쓸 필요 없음.
// 하지만 String같은 경우는 return 필요.
}
