package chapter03.lesson01; // 이 파일이 속한 폴더(패키지) 이름

import java.io.IOException;                // 파일 작업 중 생기는 에러(예외) 종류
import java.nio.charset.StandardCharsets;  // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files;                // 파일 읽기/쓰기/폴더 생성/존재 확인 도구 모음
import java.nio.file.Path;                 // 파일·폴더 경로를 표현하는 클래스

public class OverwriteJson { // 클래스 이름 = 파일 이름(OverwriteJson.java)

    // 프로그램 시작점. 파일 에러는 처리 안 하고 밖으로 던진다(throws)
    public static void main(String[] args) throws IOException {
        // data 폴더를 실제로 만든다. 이미 있으면 그냥 넘어감
        Files.createDirectories(Path.of("data"));
        // data 폴더 안의 price.json 경로를 만든다 (두 번 다 이 파일에 쓸 거라 변수로 저장)
        Path path = Path.of("data", "price.json");

        // ===== 1번째 저장 =====
        // title "menu", body "rice"인 Post 객체 생성
        Post first = new Post("menu", "rice");
        // first를 JSON 문자열로 바꿔서 price.json에 쓴다 (파일 없으면 새로 생성)
        Files.writeString(path, toJson(first), StandardCharsets.UTF_8);
        // price.json 내용을 문자열로 읽어온다
        String loaded = Files.readString(path, StandardCharsets.UTF_8);
        // "json=" 뒤에 읽어온 내용을 붙여서 출력 → json={"title":"menu","body":"rice"}
        System.out.println("json=" + loaded);

        // ===== 2번째 저장 (같은 파일!) =====
        // title "soup", body "hot"인 Post 객체 생성
        Post second = new Post("soup", "hot");
        // 같은 price.json에 또 쓴다. 이어쓰기가 아니라 덮어쓰기라서 rice 내용은 사라짐
        Files.writeString(path, toJson(second), StandardCharsets.UTF_8);
        // 다시 읽는다. String을 또 안 붙이는 이유: loaded 변수는 이미 만들었으니 값만 교체
        loaded = Files.readString(path, StandardCharsets.UTF_8);
        // 덮어쓰기 된 결과 출력 → json={"title":"soup","body":"hot"}
        System.out.println("json=" + loaded);

        // ===== post.json 확인 =====
        // 이전 실습에서 만든 data/post.json이 아직 있는지 확인해서 출력 (수정은 안 함) → kept=true
        System.out.println("kept=" + Files.exists(Path.of("data", "post.json")));
    }

    // Post 객체를 받아서 JSON 문자열로 만들어 돌려주는 메서드
    static String toJson(Post post) {
        // \" 는 문자열 안에서 큰따옴표(")를 쓰는 표기. 이어붙이면 {"title":"...","body":"..."} 모양이 됨
        return "{\"title\":\"" + post.getTitle() + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}