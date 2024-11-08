package store.inventory;

import java.util.LinkedHashMap;
import java.util.Map;
import store.product.Product;

public class Inventory {
    private static final Inventory INSTANCE = new Inventory(); // 유일한 인스턴스
    private final Map<Product, Integer> productStock = new LinkedHashMap<>(); // 제품과 수량 관리

    // private 생성자로 외부에서 인스턴스 생성 방지
    private Inventory() {
    }

    // 유일한 인스턴스를 반환
    public static Inventory getInstance() {
        return INSTANCE;
    }

    public void add(Product product, int quantity) {
        int currentQuantity = productStock.getOrDefault(product, 0); // 현재 수량 가져오기
        int updatedQuantity = currentQuantity + quantity; // 새로운 수량 계산
        productStock.put(product, updatedQuantity); // 수량 업데이트
    }

    // 특정 상품의 수량 반환
    public int getProductQuantity(Product product) {
        return productStock.getOrDefault(product, 0);
    }

    // 모든 상품 반환
    public Map<Product, Integer> getAllProducts() {
        return new LinkedHashMap<>(productStock); // 방어적 복사
    }

}
