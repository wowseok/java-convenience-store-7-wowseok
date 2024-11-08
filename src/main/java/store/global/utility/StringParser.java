package store.global.utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StringParser {
    public static List<Map<String, Object>> parseFileContent(String fileContent) {

        String[] lines = fileContent.split("\n");
        String[] headers = parseHeaders(lines[0]);
        return parseContent(lines, headers);
    }

    private static String[] parseHeaders(String headerLine) {
        return headerLine.split(",");
    }

    private static List<Map<String, Object>> parseContent(String[] lines, String[] headers) {
        List<Map<String, Object>> content = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            content.add(mapLine(lines[i], headers));
        }
        return content;
    }

    private static Map<String, Object> mapLine(String line, String[] headers) {
        String[] values = line.split(",");
        Map<String, Object> mappedLine = new LinkedHashMap<>();
        for (int j = 0; j < headers.length; j++) {
            String header = headers[j];
            String value = getValue(values, j);
            Object parsedValue = parseValue(header, value);
            mappedLine.put(header, parsedValue);
        }
        return mappedLine;
    }


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
}
