package store.product;

import java.util.LinkedHashMap;
import java.util.Map;
import store.inventory.Inventory;

public class ProductProvider {
    private static final ProductProvider INSTANCE = new ProductProvider();
    private static final Map<String, Product> existingProducts = new LinkedHashMap<>();

    private ProductProvider() {
    }

    public static ProductProvider getInstance() {
        return INSTANCE;
    }

    public Product createProduct(String name, int price, String promotion) {
        ensureBaseProductExists(name, price); // 기본 상품 보장
        return getProduct(name, price, promotion);
    }
    
    private void ensureBaseProductExists(String name, int price) {
        String baseKey = generateKey(name, price, null);
        if (!existingProducts.containsKey(baseKey)) {
            Product baseProduct = new Product(name, price, null);
            existingProducts.put(baseKey, baseProduct);

            // 기본 상품을 Inventory에 추가
            Inventory.getInstance().add(baseProduct, 0);
        }
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
