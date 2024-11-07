package store.product;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private static final Map<String, Product> existingProducts = new HashMap<>();
    private final String name;
    private final int price;

    // 외부 상태는 개별적으로 관리됨 (프로모션 변경 가능)
    private String promotion;

    // Private 생성자
    public Product(String name, int price, String promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
    }

    // 프로모션은 외부에서 변경 가능
    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    // Getter 메서드들


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPromotion() {
        return promotion;
    }


    public String toString() {
        return String.format("Product{name='%s', price=%d, promotion='%s'}", name, price, promotion);
    }
}

