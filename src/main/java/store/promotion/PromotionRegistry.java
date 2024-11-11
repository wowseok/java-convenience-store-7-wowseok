package store.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionRegistry {
    private final Map<String, Promotion> promotionMap;

    public PromotionRegistry(List<Promotion> promotions) {
        this.promotionMap = new HashMap<>();
        for (Promotion promotion : promotions) {
            promotionMap.put(promotion.getName(), promotion); // 프로모션 이름으로 매핑
        }
    }

    public Promotion getPromotionByName(String promotionName) {
        return promotionMap.getOrDefault(promotionName, null);
    }
}
