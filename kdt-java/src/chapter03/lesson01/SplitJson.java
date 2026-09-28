package chapter03.lesson01; // 이 파일이 속한 폴더(패키지) 이름

import java.io.IOException;                // 파일 작업 중 생기는 에러(예외) 종류
import java.nio.charset.StandardCharsets;  // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files;                // 파일 읽기/쓰기/폴더 생성 도구 모음
import java.nio.file.Path;                 // 파일·폴더 경로를 표현하는 클래스

public class SplitJson {
    // 프로그램 시작점. 파일 에러는 처리 안 하고 밖으로 던진다(throws)
    public static void main(String[] args) throws IOException {
        // data 폴더를 만든다. 이미 있으면 그냥 넘어감
        Files.createDirectories(Path.of("data"));

        // 게시글 2개 생성
        Post first = new Post("closed", "no class");
        Post second = new Post("exam", "bring id");

        // ===== 1. 게시글 2개 → 배열 JSON (pair.json) =====
        // 바깥을 [ ]로 감싸고, 두 객체 사이에만 쉼표. 마지막 뒤에는 쉼표 없음!
        String array = "[" + toJson(first) + "," + toJson(second) + "]";
        // pair.json 경로
        Path pairPath = Path.of("data", "pair.json");
        // 배열 문자열을 파일에 쓴다
        Files.writeString(pairPath, array, StandardCharsets.UTF_8);
        // 다시 읽어서 출력 → json=[{...},{...}]
        System.out.println("json=" + Files.readString(pairPath, StandardCharsets.UTF_8));

        // ===== 2. closed 게시글 1개 → 객체 JSON (one.json) =====
        // 하나만 저장하니까 [ ] 없이 객체 한 줄만
        Path onePath = Path.of("data", "one.json");
        // first만 JSON으로 바꿔서 쓴다
        Files.writeString(onePath, toJson(first), StandardCharsets.UTF_8);
        // 다시 읽어서 출력 → one={...}
        System.out.println("one=" + Files.readString(onePath, StandardCharsets.UTF_8));

        // posts.json은 요구사항대로 아예 건드리지 않음 (코드에 안 쓰면 됨)
    }

    // Post 객체를 JSON 객체 문자열로 바꿔주는 메서드
    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle() + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}