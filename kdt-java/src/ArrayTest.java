import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
//        String[] titles2 = new String[3];
//        titles2[0] = "closed";
//        String[] titles = {"closed", "exam", "holiday"};
//        System.out.println(titles[0]);
//        System.out.println(titles);
//        System.out.println(titles2);
//
//
//        String[] titles3 = new String[3];
//        System.out.println(titles3[0]); // null값 리턴
//
//        int[] ints = new int[5];
//        System.out.println(ints[0]); // 0 리턴

        int[] titles = {1, 2, 3};
        int[] titles2 = titles; // 객체 주소가 공유되어 아래 동일한 결과값을 보여줌
        System.out.println(titles);
        System.out.println(titles2);

        titles2[0] = 99;

        System.out.println(titles[0]);

//        int[] clone = titles.clone(); // 배열 전체복사
//        int a;
//        int b = 1;

//        int[][] ints = new int[3][2]; // 3*2 행렬 (부모가 3 자식이 2) ??? 멀리서 보면 3개인데 안에 보면 각 하나마다 2개씩 붙어있는거?? 부모가 베이스인가보네?
//        int[] ints = new int[3];
//
//        for (int i = 0; i < ints.length; i++) {
//            System.out.println(i + "=" + ints[i]);
//        }
//
//        for (int idx : ints) {
//            System.out.println(idx);
//        }
//
////        int[][] data = {{1, 2, {3, 4}}};
//        int[][] dataCopy = new int[data.length][];
//
//        for(int i = 0; i < data.length; i++) {
//            dataCopy[i] = data[i].clone();
//        }
//

    }
}
