package chapter03.lesson01;

import java.io.IOException; // 파일 다루다 생기는 에러(예외) 종류 (IO는 Input Output의 약자)
import java.nio.charset.StandardCharsets; // 글자 인코딩(UTF-8 등) 모음
import java.nio.file.Files; // 파일 읽기/쓰기/생성 도구 모음
import java.nio.file.Path; // 파일·폴더 경로를 표현하는 클래스

public class TwiceWrite {
    public static void main(String[] args) throws IOException { // 프로그램 시작점. 파일 작업 중 에러가 나면 밖으로 던지겠다(throws)
        Path dir = Path.of("data"); // 파일이나 폴더의 위치 경로를 표현하는 객체 // "data"라는 폴더 경로를 Path 객체로 만든다 (아직 폴더가 생긴 건 아님, 주소만 적은 것)

        Files.createDirectories(dir); // 위 경로에 실제로 폴더를 만든다. 이미 있으면 그냥 넘어가고 에러 안 남

        Path path = Path.of("data","tray.txt"); // data 폴더 안의 tray.txt 파일 경로를 만든다 (아직 파일은 없어도 됨)

        Files.writeString(path, "kimbap\n", StandardCharsets.UTF_8); // tray.txt에 "kimbap"을 쓴다. 파일이 없으면 새로 만들어짐. \n은 줄바꿈 (참고로 Files.writeString() 기본은 덮어쓰기!)

        String text = Files.readString(path, StandardCharsets.UTF_8); // tray.txt 내용을 통째로 문자열로 읽어온다
        System.out.println("text=" + text.stripTrailing()); // 끝의 줄바꿈(\n)을 잘라내고 출력 → text=kimbap

        Files.writeString(path, "cookie\n", StandardCharsets.UTF_8); // 같은 파일에 "cookie"를 또 쓴다. 이어쓰기가 아니라 덮어쓰기라서 kimbap은 사라짐!


        text = Files.readString(path, StandardCharsets.UTF_8); // 다시 읽는다 (변수 text는 새로 선언 안 하고 값만 교체)
        System.out.println("text=" + text.stripTrailing()); // 출력 → text=cookie

        Path missing = Path.of("data", "tray-missing.txt"); // 존재하지 않는 파일의 경로를 만든다 (경로만 만든 거라 파일은 안 생김)
        System.out.println("missing=" + Files.exists(missing)); // 그 파일이 실제로 있는지 확인 → false 출력

    }
}
