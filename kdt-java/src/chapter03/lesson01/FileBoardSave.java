package chapter03.lesson01; // 이 파일이 속한 폴더(패키지) 이름

import java.io.IOException;                // 추가: 파일 에러(예외) 종류
import java.nio.charset.StandardCharsets;  // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files;                // 파일 읽기/쓰기/폴더 생성/존재 확인 도구 모음
import java.nio.file.Path;                 // 파일·폴더 경로를 표현하는 클래스
import java.util.ArrayList;                // 게시글 여러 개를 담는 리스트

public class FileBoardSave {
    // 수정: throws IOException 추가 (파일 에러는 밖으로 던진다)
    public static void main(String[] args) throws IOException {
        // 게시글을 담을 리스트 생성 (파이썬의 빈 리스트 [] 와 같음)
        ArrayList<Post> posts = new ArrayList<>();

        // 게시글 3개를 리스트에 추가 (파이썬의 append 와 같음)
        posts.add(new Post("closed", "no class"));
        posts.add(new Post("exam", "bring id"));
        posts.add(new Post("kimbap", "sold out"));

        // 리스트 전체를 JSON 배열 문자열로 바꾼다 (아래 toArrayJson 호출)
        String json = toArrayJson(posts);

        // data 폴더를 만든다. 이미 있으면 그냥 넘어감 (수정: Paths.get → Path.of)
        Files.createDirectories(Path.of("data"));
        // data 폴더 안의 board.json 경로를 만든다
        Path path = Path.of("data", "board.json");
        // JSON 배열 문자열을 board.json에 UTF-8로 쓴다
        Files.writeString(path, json, StandardCharsets.UTF_8);

        // 방금 저장한 파일을 다시 읽어온다
        String loaded = Files.readString(path, StandardCharsets.UTF_8);
        // 읽어온 내용 출력 → json=[{...},{...},{...}]
        System.out.println("json=" + loaded);

        // board.json이 실제로 존재하는지 확인해서 출력 → exists=true
        System.out.println("exists=" + Files.exists(path));
    }

    // Post 여러 개가 담긴 리스트를 받아서 JSON 배열 문자열로 만들어 돌려주는 메서드
    static String toArrayJson(ArrayList<Post> posts) {
        String json = "[";                          // 여는 대괄호로 시작
        for (int i = 0; i < posts.size(); i++) {    // 리스트 0번부터 끝까지 반복
            if (i > 0) {                            // 첫 번째만 빼고
                json = json + ",";                  // 앞에 쉼표를 붙인다 (마지막 뒤 쉼표 방지)
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