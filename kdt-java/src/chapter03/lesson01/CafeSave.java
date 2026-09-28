package chapter03.lesson01; // 이 파일이 속한 폴더(패키지) 이름

import java.io.IOException;                // 파일 작업 중 생기는 에러(예외) 종류
import java.nio.charset.StandardCharsets;  // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files;                // 파일 읽기/쓰기/폴더 생성/존재 확인 도구 모음
import java.nio.file.Path;                 // 파일·폴더 경로를 표현하는 클래스

public class CafeSave {
    // 프로그램 시작점. 파일 에러는 처리 안 하고 밖으로 던진다(throws)
    public static void main(String[] args) throws IOException {
        // data 폴더를 만든다. 이미 있으면 그냥 넘어감
        Files.createDirectories(Path.of("data"));

        // Post 객체 2개 생성 (title, body 순서)
        Post a = new Post("pork", "6000won");
        Post b = new Post("water", "1000won");

        // 두 객체를 [ ]로 감싸고, 사이에만 쉼표를 넣어서 JSON 배열 문자열로 만든다
        String json = "[" + toJson(a) + "," + toJson(b) + "]";

        // data/cafe.json 경로
        Path path = Path.of("data", "cafe.json");
        // JSON 문자열을 파일에 UTF-8로 쓴다 (파일 없으면 새로 생성)
        Files.writeString(path, json, StandardCharsets.UTF_8);

        // 파일을 다시 읽어서 출력 → json=[{...},{...}]
        System.out.println("json=" + Files.readString(path, StandardCharsets.UTF_8));
        // 파일이 실제로 있는지 출력 → exists=true
        System.out.println("exists=" + Files.exists(path));
    }

    // Post 객체 하나를 JSON 객체 문자열로 바꿔주는 메서드
    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle() + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}