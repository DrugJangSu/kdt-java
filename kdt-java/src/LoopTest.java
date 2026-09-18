public class LoopTest {
    public static void main(String[] args) {
        // for(시작값; 조건; 마지막 순회에서 어떻게 처리할 것인가) {
        // 반복문장
        // }
        for (int n = 1; n < 11; n++) { // 1 ~ 10
            System.out.println("closed day " + n);
        }

        for (int n = 10; n < 101; n++) { // 10 ~ 100
            System.out.println("closed day " + n);
        }
        for (int n = 10; n <= 100; n++) { // 10 ~ 100
            System.out.println("closed day " + n);
        }
        for (int n = 100; n > 0; n--) { // 100 ~ 1
            System.out.println("closed day " + n);
        }

        for (int n = 2; n <= 100; n *= 2) { // 2씩 곱함(n = n * 2)
            System.out.println("closed day " + n);
        }

//
//        int n = 1;
//        while (n < 6) {
//            System.out.println(n);
//            n = n + 1;
//        }

//        int n = 1;
//        while (true) { // while문은 주로 Break 많이 씀. (~~가 true 인 동안에는)
//            // 만약에 n이 10 이상이다 break
//            if (n >= 10) {
//                break;
//            }
//            n = n + 1;
//        }
    // 하지만 대게 실무에서는 for문을 더 많이 쓰곤 했다고...

        // 이중 for문(구구단)
        for (int n=2; n < 10; n++) { // n 한번 돌때마다 i가 9바퀴 돔.

            for(int i=1; i < 10; i++) {
                System.out.println(n + "*" + i + "=" + n * i);
            }
        }

    }
}

