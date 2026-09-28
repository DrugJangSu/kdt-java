package chapter03.lesson01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilePostList {
    public static void main(String[] args) throws IOException {
        Post a = new Post("closed", "no class");
        Post b = new Post("exam", "bring id");

        String json = "[" + toJson(a) + ", " + toJson(b) + "]";

        Files.createDirectories(Path.of("data"));
        Path path = Path.of("data", "posts.json");

        Files.writeString(path, json, StandardCharsets.UTF_8); // 해당 파일에 문자열을 UTF-8 방식으로 저장

        System.out.println(Files.readString(path, StandardCharsets.UTF_8));   // 해당 파일의 내용을 UTF-8 방식으로 읽어서 String에 저장
    }

    // Post 객체를 받아서 JSON 문자열로 만들어 돌려주는 메서드
    static String toJson(Post post) {
        // \" 는 문자열 안에서 큰따옴표(")를 쓰기 위한 표기. 이어붙이면 {"title":"closed","body":"no class"} 가 됨
        return "{\"title\":\"" + post.getTitle() + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}
