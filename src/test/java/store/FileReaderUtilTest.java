package store;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.mvc.utility.FileReaderUtil;

public class FileReaderUtilTest {

    @Test
    @DisplayName("파일을 정상적으로 읽어오는지 확인한다.")
    void testReadFileContent() throws IOException {
        // Arrange: 임시 파일 생성 및 내용 작성
        String expectedContent = String.join("\n", "name,price,quantity,promotion",
                "콜라,1000,10,탄산2+1",
                "콜라,1000,10,null",
                "사이다,1000,8,탄산2+1",
                "사이다,1000,7,null",
                "오렌지주스,1800,9,MD추천상품",
                "탄산수,1200,5,탄산2+1",
                "물,500,10,null",
                "비타민워터,1500,6,null",
                "감자칩,1500,5,반짝할인",
                "감자칩,1500,5,null",
                "초코바,1200,5,MD추천상품",
                "초코바,1200,5,null",
                "에너지바,2000,5,null",
                "정식도시락,6400,8,null",
                "컵라면,1700,1,MD추천상품",
                "컵라면,1700,10,null"
        ) + "\n";
        // Assert: 읽은 내용이 예상 내용과 일치하는지 확인
        assertThat(FileReaderUtil.readFile("src/test/resources/products.md")).isEqualTo(expectedContent);
    }
}
