package store.inventory;

import store.product.Product;

public class GeneralProductFactory {
    public static Product createGeneralProduct(Product product) {
        return new Product(product.getName(), product.getPrice(), 0, null);
    }
}

