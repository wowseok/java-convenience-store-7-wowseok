package store.mvc.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderUtil {

    public static String readFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath)) // 파일 한번에 읽기
                    .replace("\r\n", "\n"); // 모든 CRLF 줄바꿈을 LF로 표준화
        } catch (IOException e) {
            // IOException 처리: 파일을 읽을 수 없는 경우
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
