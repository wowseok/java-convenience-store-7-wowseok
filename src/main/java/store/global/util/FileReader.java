package store.global.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static String readFile(String filePath) {
        try {
            // 파일 내용을 읽고 \r 제거
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return content.replace("\r", ""); // \r 제거
        } catch (IOException e) {
            // 예외 발생 시 처리할 방법 (예: 로그 출력, 빈 문자열 반환 등)
            System.out.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
            return null;  // 오류 발생 시 null 반환
        }
    }
}
