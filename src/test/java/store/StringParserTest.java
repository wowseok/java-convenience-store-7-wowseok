package store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import store.global.utility.FileReaderUtil;
import store.global.utility.StringParser;

public class StringParserTest {

    @Test
    void name() {
        List<Map<String, String>> data = StringParser.parse(
                FileReaderUtil.readFile("src/test/resources/products.md"));
        // Then: 파싱된 데이터 검증
        assertEquals(16, data.size(), "파싱된 데이터의 개수가 맞아야 함");

        // 첫 번째 데이터 검증
        Map<String, String> firstRow = data.get(0);
        assertEquals("콜라", firstRow.get("name"));
        assertEquals("1000", firstRow.get("price"));
        assertEquals("10", firstRow.get("quantity"));
        assertEquals("탄산2+1", firstRow.get("promotion"));

        // 두 번째 데이터 검증
        Map<String, String> secondRow = data.get(1);
        assertEquals("콜라", secondRow.get("name"));
        assertEquals("1000", secondRow.get("price"));
        assertEquals("10", secondRow.get("quantity"));
        assertNull(secondRow.get("promotion"), "promotion 값이 null이어야 함");

    }
}
