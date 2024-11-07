package store.product;

import java.util.HashMap;
import java.util.Map;

public class ProductFactory {
    private static final Map<String, Product> existingProducts = new HashMap<>();

    public static Product createProduct(String name, int price, String promotion) {
        String key = generateKey(name, price, promotion); //이름, 가격, 프로모션을 기준으로 key 생성

        if (existingProducts.containsKey(key)) {
            return existingProducts.get(key);
        }

        Product newProduct = new Product(name, price, promotion);
        existingProducts.put(key, newProduct);
        return newProduct;
    }

    private static String generateKey(String name, int price, String promotion) {
        return String.format("%s:%d:%s", name, price, promotion);
    }
}
