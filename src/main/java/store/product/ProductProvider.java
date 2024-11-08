package store.product;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductProvider {
    private static final ProductProvider INSTANCE = new ProductProvider();
    private static final Map<String, Product> existingProducts = new LinkedHashMap<>();

    private ProductProvider() {
    }

    public static ProductProvider getInstance() {
        return INSTANCE;
    }

    public Product createProduct(String name, int price, String promotion) {
        // 기본 상품 생성 보장
        initializeBaseProduct(name, price);

        // 프로모션 상품 생성 또는 반환
        return getProduct(name, price, promotion);
    }

    private void initializeBaseProduct(String name, int price) {
        String baseKey = generateKey(name, price, null);
        if (!existingProducts.containsKey(baseKey)) {
            Product baseProduct = new Product(name, price, null);
            existingProducts.put(baseKey, baseProduct);
        }
    }

    // 기본 상품 조회 메서드 추가
    public Product getBaseProduct(String name, int price) {
        initializeBaseProduct(name, price);
        String baseKey = generateKey(name, price, null);
        return existingProducts.get(baseKey);
    }


    private Product getProduct(String name, int price, String promotion) {
        String key = generateKey(name, price, promotion);
        if (!existingProducts.containsKey(key)) {
            Product newProduct = new Product(name, price, promotion);
            existingProducts.put(key, newProduct);
        }
        return existingProducts.get(key);
    }

    private String generateKey(String name, int price, String promotion) {
        return String.format("%s:%d:%s", name, price, promotion);
    }

    public Map<String, Product> getAllProducts() {
        return new LinkedHashMap<>(existingProducts); // 기존 맵의 복사본 반환
    }
}
