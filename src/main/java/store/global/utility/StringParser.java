package store.global.utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StringParser {
    public static List<Map<String, String>> parseFileContent(String fileContent) {
        String[] lines = fileContent.split("\n");
        String[] headers = parseHeaders(lines[0]);
        return parseData(lines, headers);
    }

    private static String[] parseHeaders(String headerLine) {
        return headerLine.split(",");
    }

    private static List<Map<String, String>> parseData(String[] lines, String[] headers) {
        List<Map<String, String>> parsedDataList = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            Map<String, String> parsedData = mapLineToData(lines[i], headers);
            parsedDataList.add(parsedData);
        }
        return parsedDataList;
    }

    private static Map<String, String> mapLineToData(String line, String[] headers) {

        String[] values = line.split(",");
        Map<String, String> parsedData = new LinkedHashMap<>();
        for (int j = 0; j < headers.length; j++) {
            parsedData.put(headers[j], getValue(values, j));
        }

        return parsedData;
    }

    private static String getValue(String[] values, int index) {
        if (values[index].equals("null") || values[index].isEmpty()) {
            return null;
        }
        if (index < values.length) {
            return values[index];
        }
        return null;
    }
}
