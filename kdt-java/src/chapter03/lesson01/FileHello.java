package chapter03.lesson01;

import java.io.IOException; // 파일 다루다 생기는 에러(예외) 종류 (IO는 Input Output의 약자)
import java.nio.charset.StandardCharsets; // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files; // 파일 읽기/쓰기/생성 도구 모음
import java.nio.file.Path; // 파일·폴더 경로를 표현하는 클래스

public class FileHello {
    public static void main(String[] args) throws IOException {
        Path dir = Path.of("data"); // 파일이나 폴더의 위치 경로를 표현하는 객체

        Files.createDirectories(dir); // 해당 경로에 폴더를 생성함
                                        // 이미 폴더가 존재하면 그대로 넘어감

        Path path = Path.of("data","hello.txt");  // data 폴더 안의 hello.txt 파일 위치를 지정

        Files.writeString(path, "kimbap\n", StandardCharsets.UTF_8); // 해당 파일에 문자열을 UTF-8 방식으로 저장

        String text = Files.readString(path, StandardCharsets.UTF_8);  // 해당 파일의 내용을 UTF-8 방식으로 읽어서 String에 저장

        System.out.println("text = " + text.stripTrailing()); // text.stripTrailing() : 문자열의 끝 공백 문자와 줄바꿤 제거
        System.out.println("exists = + Files.exists(path)"); // Files.exists(path) : 해당 경로에 파일이나 폴더가 존재하는지 확인하고 true 또는 false를 반환(boolean)
        System.out.println("path = " + path.toAbsolutePath()); // 상대경로(path)를 가지고 실제 절대경로 형태로 바꿔서 보여주는 것
    }
}

