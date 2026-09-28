package chapter03.lesson01; // 이 파일이 속한 폴더(패키지) 이름

import java.io.IOException;                // 파일 작업 중 생기는 에러(예외) 종류
import java.nio.charset.StandardCharsets;  // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files;                // 파일 읽기/쓰기/폴더 생성/존재 확인 도구 모음
import java.nio.file.Path;                 // 파일·폴더 경로를 표현하는 클래스
import java.util.ArrayList;                // 게시글 여러 개를 담는 리스트

public class RollJson {
    // 프로그램 시작점. 파일 에러는 처리 안 하고 밖으로 던진다(throws)
    public static void main(String[] args) throws IOException {
        // data 폴더를 만든다. 이미 있으면 그냥 넘어감
        Files.createDirectories(Path.of("data"));

        // ===== 1. 빈 목록 → empty.json =====
        // 아무것도 안 담은 빈 리스트
        ArrayList<Post> emptyPosts = new ArrayList<>();
        // empty.json 경로
        Path emptyPath = Path.of("data", "empty.json");
        // 빈 리스트를 JSON 배열로 바꿔서 쓴다 → "[]"
        Files.writeString(emptyPath, toArrayJson(emptyPosts), StandardCharsets.UTF_8);
        // 다시 읽어서 출력 → empty=[]
        System.out.println("empty=" + Files.readString(emptyPath, StandardCharsets.UTF_8));

        // ===== 2. 게시글 3개 → roll.json =====
        // 다른 리스트를 새로 만든다 (emptyPosts와 별개)
        ArrayList<Post> rollPosts = new ArrayList<>();
        rollPosts.add(new Post("closed", "no class"));
        rollPosts.add(new Post("exam", "bring id"));
        rollPosts.add(new Post("kimbap", "sold out"));
        // roll.json 경로
        Path rollPath = Path.of("data", "roll.json");
        // 리스트를 JSON 배열로 바꿔서 쓴다
        Files.writeString(rollPath, toArrayJson(rollPosts), StandardCharsets.UTF_8);
        // 다시 읽어서 출력 → json=[{...},{...},{...}]
        System.out.println("json=" + Files.readString(rollPath, StandardCharsets.UTF_8));
        // roll.json이 실제로 있는지 출력 → exists=true
        System.out.println("exists=" + Files.exists(rollPath));

        // board.json은 요구사항대로 건드리지 않음 (코드에 안 쓰면 됨)
    }

    // Post 여러 개가 담긴 리스트를 JSON 배열 문자열로 만들어 돌려주는 메서드
    static String toArrayJson(ArrayList<Post> posts) {
        String json = "[";                          // 여는 대괄호로 시작
        for (int i = 0; i < posts.size(); i++) {    // 리스트 크기만큼 반복 (빈 리스트면 0번 돌고 끝)
            if (i > 0) {                            // 첫 번째만 빼고
                json = json + ",";                  // 앞에 쉼표를 붙인다
            }
            json = json + toJson(posts.get(i));     // i번째 게시글을 JSON 객체로 바꿔서 붙인다
        }
        json = json + "]";                          // 닫는 대괄호
        return json;                                // 완성된 문자열 돌려주기
    }

    // Post 객체 하나를 JSON 객체 문자열로 바꿔주는 메서드
    static String toJson(Post post) {
        return "{\"title\":\"" + post.getTitle() + "\",\"body\":\"" + post.getBody() + "\"}";
    }
}

// 직렬화 (Serialization) = 메모리 속 객체를 → 파일/네트워크로 보낼 수 있는 문자열(또는 바이트)로 바꾸는 것 (toJson, toArrayJson : 직렬화 메서드)
// 역직렬화 (Deserialization) = 그 문자열을 → 다시 객체로 되돌리는 것