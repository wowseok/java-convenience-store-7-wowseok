package store.inventory;

import java.util.Map;
import store.product.Product;
import store.product.ProductFormatter;

public class ProductOutputView {

    public static void displayProducts() {
        Inventory inventory = Inventory.getInstance(); // Singleton 인스턴스 가져오기
        Map<Product, Integer> products = inventory.getAllProducts();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            // 포맷팅된 데이터를 출력
            System.out.println(formatProduct(product, quantity));

        }
        System.out.println();
    }

    private static String formatProduct(Product product, int quantity) {
        // ProductFormatter ENUM을 활용하여 포맷팅
        String name = ProductFormatter.NAME.format(product.getName());
        String price = ProductFormatter.PRICE.format(product.getPrice());
        String quantityStr = ProductFormatter.QUANTITY.format(quantity);
        String promotion = ProductFormatter.PROMOTION.format(product.getPromotion());

        return String.format("- %s %s %s %s", name, price, quantityStr, promotion);
    }
}
