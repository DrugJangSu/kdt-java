package chapter03.lesson01; // 이 파일이 속한 폴더(패키지) 이름

import java.io.IOException;                // 파일 작업 중 생기는 에러(예외) 종류
import java.nio.charset.StandardCharsets;  // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files;                // 파일 읽기/쓰기/폴더 생성 도구 모음
import java.nio.file.Path;                 // 파일·폴더 경로를 표현하는 클래스
import java.nio.file.Paths;                // 안 쓰는 import (지워도 됨)


public class FilePostJson { // 클래스 이름 = 파일 이름(FilePostJson.java)
    // 프로그램 시작점. 파일 작업 에러는 처리하지 않고 밖으로 던진다(throws)
    public static void main(String[] args) throws IOException {
        // Post 객체 생성: title은 "closed", body는 "no class"
        Post post = new Post("closed", "no class");

        // Post 객체를 JSON 형태의 문자열로 바꾼다 (아래 toJson 메서드 호출)
        String json = toJson(post);

        // "data" 폴더를 실제로 만든다. 이미 있으면 그냥 넘어감
        Files.createDirectories(Path.of("data"));
        // data 폴더 안의 post.json 파일 경로를 만든다 (경로만 만든 것, 파일은 아직 없음)
        Path path = Path.of("data", "post.json");

        // JSON 문자열을 post.json 파일에 UTF-8로 쓴다 (파일이 없으면 새로 생성, 있으면 덮어쓰기)
        Files.writeString(path, json, StandardCharsets.UTF_8);

        // 방금 저장한 post.json 내용을 문자열로 다시 읽어온다
        String loaded = Files.readString(path, StandardCharsets.UTF_8);
        // 읽어온 내용을 출력 → {"title":"closed","body":"no class"}
        System.out.println(loaded);

    }

    // Post 객체를 받아서 JSON 문자열로 만들어 돌려주는 메서드
    static String toJson(Post post) {
        // \" 는 문자열 안에서 큰따옴표(")를 쓰기 위한 표기. 이어붙이면 {"title":"closed","body":"no class"} 가 됨
        return "{\"title\":\"" + post.getTitle() + "\",\"body\":\"" + post.getBody() + "\"}";
    }


}
