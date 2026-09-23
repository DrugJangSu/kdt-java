package chapter02.lesson07;

import java.util.List;

public class FlatMapDemo {
    public static void main(String[] args) {
        List<List<String>> tagRows = List.of(List.of("java", "stream"), List.of("stream", "lambda"));


        //        List<String> tags = tagRows.stream().flatMap(List::stream).toList();
        List<String> tags = tagRows.stream().flatMap(List::stream).distinct().sorted().toList();

        System.out.println(tags);


    }
}