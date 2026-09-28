package chapter02.lesson07;

import chapter02.chicken.ChickenNotFoundException;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> hit = Optional.of("김밥");
//        System.out.println(hit.get());
        Optional<String> empty = Optional.empty();
        hit.orElse("품절");
        System.out.println("hit = " + hit.orElseGet(() -> "품절")); // 없으면 다음을 반환한다
        System.out.println("miss = " + empty.orElseGet(() -> "품절"));
        hit.ifPresent(value -> System.out.println("선택 = " + value));
        // 값이 없다면 새로운 예외(Exception)를 던진다.
        hit.orElseThrow(
                () -> new ChickenNotFoundException(1)
        );

    }
}