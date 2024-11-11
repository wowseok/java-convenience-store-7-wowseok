package store.global.util;

import java.util.ArrayList;
import java.util.List;
import store.promotion.Promotion;

public class PromotionDataProcessor {

    public static List<Promotion> processPromotionData(String data) {
        String[] lines = data.split("\n");
        List<String[]> promotionData = extractPromotionData(lines);
        return convertToPromotions(promotionData);
    }

    private static List<String[]> extractPromotionData(String[] lines) {
        List<String[]> promotionData = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) { // Skip header
            promotionData.add(lines[i].split(","));
        }
        return promotionData;
    }

    private static List<Promotion> convertToPromotions(List<String[]> promotionData) {
        List<Promotion> promotions = new ArrayList<>();
        for (String[] fields : promotionData) {
            String name = fields[0];
            int buy = Integer.parseInt(fields[1]);
            int get = Integer.parseInt(fields[2]);
            String startDate = fields[3];
            String endDate = fields[4];
            promotions.add(new Promotion(name, buy, get, startDate, endDate));
        }
        return promotions;
    }
}
