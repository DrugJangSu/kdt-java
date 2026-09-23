package chapter02.lesson07;

import java.util.List;

public class StreamPipelineDemo {
    public static void main(String[] args) {
    List<String> titles = List.of("closed", "exam", "closed", "quiz");
    List<String> top = titles.stream()
            .filter(t -> t.startsWith("c") || t.startsWith("e"))
            .map(String::toUpperCase)
            .distinct()
            .sorted()
            .limit(2)
            .toList();
    boolean allShort = titles.stream()
            .allMatch(t -> t.length() <= 6);

    System.out.println("top = " + top);
    System.out.println("allShort = " + allShort);

    }

}
