package store.product;

import java.util.Map;

public class ProductMapper {
    public static ProductDTO mapToProduct(Map<String, String> data) {
        return new ProductDTO(
                data.get(ProductFields.NAME.getKey()),
                Integer.parseInt(data.get(ProductFields.PRICE.getKey())),
                Integer.parseInt(data.get(ProductFields.QUANTITY.getKey())),
                data.get(ProductFields.PROMOTION.getKey())
        );
    }
}
