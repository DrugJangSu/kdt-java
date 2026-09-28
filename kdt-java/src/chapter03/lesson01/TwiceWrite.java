package chapter03.lesson01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TwiceWrite {
    public static void main(String[] args) throws IOException {
        Path dir = Path.of("data"); // 파일이나 폴더의 위치 경로를 표현하는 객체

        Files.createDirectories(dir);

        Path path = Path.of("data","tray.txt");

        Files.writeString(path, "kimbap\n", StandardCharsets.UTF_8);

        String text = Files.readString(path, StandardCharsets.UTF_8);
        System.out.println("text=" + text.stripTrailing());

        Files.writeString(path, "cookie\n", StandardCharsets.UTF_8);

        text = Files.readString(path, StandardCharsets.UTF_8);
        System.out.println("text=" + text.stripTrailing());

        Path missing = Path.of("data", "tray-missing.txt");
        System.out.println("missing=" + Files.exists(missing));

    }
}
