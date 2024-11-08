package store.global.utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StringParser {


    public static List<Map<String, String>> parse(String fileContent) {
        String[] lines = fileContent.split("\n");
        String[] headers = parseHeaders(lines[0]);
        return parseContent(lines, headers);
    }

    private static String[] parseHeaders(String headerLine) {
        return headerLine.split(",", -1);
    }


    public static List<Map<String, String>> parseContent(String[] lines, String[] headers) {
        List<Map<String, String>> content = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) { // 첫 줄은 헤더이므로 건너뜀
            Map<String, String> mappedLine = linkHeadersAndValues(headers, lines[i]);
            content.add(mappedLine);
        }
        return content;
    }

    private static Map<String, String> linkHeadersAndValues(String[] headers, String line) {
        String[] values = line.split(",", -1);
        Map<String, String> mappedLine = new LinkedHashMap<>();
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            String value = convertNull(values[i]);
            mappedLine.put(header, value);
        }
        return mappedLine;
    }

    // "null" 문자열을 실제 null로 변환
    private static String convertNull(String value) {
        if ("null".equalsIgnoreCase(value)) {
            return null;
        }
        return value;
    }
/*
    private static String extractValue(String[] values, int index) {
        if (index >= values.length) {
            return null;
        }
        String value = values[index];
        return (value == null || value.isEmpty() || value.equalsIgnoreCase("null")) ? null : value;
    }
*/



/*
    private static String getValue(String[] values, int index) {
        if (index >= values.length) {
            return null;
        }
        String value = values[index];
        if (value.isEmpty() || value.equals("null")) {
            return null;
        }
        return value;
    }

    private static Object parseValue(String header, String value) {
        if (value == null) {
            return null;
        }
        if ("price".equals(header) || "quantity".equals(header)) {
            return Integer.parseInt(value);
        }
        return value;
    }

 */
}
