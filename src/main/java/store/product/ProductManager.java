package store.product;

import java.util.List;
import java.util.Map;
import store.inventory.Inventory;

public class ProductManager {
    private final Inventory inventory;
    private final ProductProvider productProvider;

    public ProductManager() {
        this.inventory = Inventory.getInstance();
        this.productProvider = ProductProvider.getInstance();
    }

    // 상품을 등록하다.
    public void registerProduct(List<Map<String, Object>> productData) {
        for (Map<String, Object> data : productData) {
            // 단계별 작업 수행
            processProductData(data);
        }
    }

    private void processProductData(Map<String, Object> data) {
        // 1. 데이터 추출
        String name = extractName(data);
        int price = extractPrice(data);
        int quantity = extractQuantity(data);
        String promotion = extractPromotion(data);

        // 2. 기본 상품 보장
        ensureBaseProductInInventory(name, price);

        // 3. 상품을 인벤토리로
        moveProductToInventory(name, price, promotion, quantity);
    }

    private String extractName(Map<String, Object> data) {
        return (String) data.get("name");
    }

    private int extractPrice(Map<String, Object> data) {
        return (int) data.get("price");
    }

    private int extractQuantity(Map<String, Object> data) {
        return (int) data.get("quantity");
    }

    private String extractPromotion(Map<String, Object> data) {
        return (String) data.get("promotion");
    }

    private void ensureBaseProductInInventory(String name, int price) {
        Product baseProduct = productProvider.getBaseProduct(name, price);
        addProductToInventory(baseProduct, 0); // 기본 상품 수량 0으로 추가
    }

    private void moveProductToInventory(String name, int price, String promotion, int quantity) {
        Product product = productProvider.createProduct(name, price, promotion);
        addProductToInventory(product, quantity);
    }

    // 상품과 수량을 인벤토리에 추가
    private void addProductToInventory(Product product, int quantity) {
        inventory.add(product, quantity);
    }
}
