import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> titles = new ArrayList<>(); // 여기서 꺽쇠 <>이거는 특정 타입만 넣을 수 있도록 한 것. 여기서 예시는 String만 가능하게 함

        titles.add("closed");
        titles.add("exam");
        titles.add("holiday"); // (CRUD 중에서) Create

        System.out.println(titles.size()); // length마냥 Array에서는 size가 같은 역할을 함.
        System.out.println(titles.get(0)); // (CRUD 중에서) Read
//        System.out.println(titles.get(3)); // 범위초과
//        System.out.println(titles.getClass()); // 어떤 클래스를 쓰는지 확인하는 동작

        titles.set(1, "makeup"); // 1번째 인덱스에 있는 값을 makeup으로 바꿔라. // (CRUD 중에서) Update

        titles.remove(2); // 마지막 값을 ArrayList에서 삭제 // (CRUD 중에서) Delete


        ArrayList<String> titles2 = new ArrayList<>();

//        System.out.println(titles2.getFirst()); // 요소없음 에러



//        titles.removeLast();

    }
}
