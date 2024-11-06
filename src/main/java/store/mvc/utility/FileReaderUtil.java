package store.mvc.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtil {

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.strip()).append(System.lineSeparator()); // 줄바꿈 추가
            }
        } catch (IOException e) {
            System.out.println("[ERROR] 파일을 읽는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return content.toString().replaceAll("\\r\\n", "\n");
    }
}
