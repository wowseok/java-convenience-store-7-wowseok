package store.product;

import java.util.List;
import store.inventory.Inventory;

public class ProductManager {
    private final Inventory inventory;
    private final ProductProvider productProvider;

    public ProductManager() {
        this.inventory = Inventory.getInstance();
        this.productProvider = ProductProvider.getInstance();
    }

    // 상품 데이터를 등록하다
    public void registerProducts(List<ProductDTO> productData) {
        for (ProductDTO dto : productData) {
            processProduct(dto);
        }
    }

    private void processProduct(ProductDTO dto) {
        // 1. 상품을 인벤토리에 추가
        addProductToInventory(dto);
    }

    private void addProductToInventory(ProductDTO dto) {
        Product product = productProvider.createProduct(dto.getName(), dto.getPrice(), dto.getPromotion());
        inventory.add(product, dto.getQuantity());
    }
}
