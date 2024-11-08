package store.product;

import store.global.FieldEnum;

// Product 필드 Enum
public enum ProductFields implements FieldEnum {
    NAME("name"),
    PRICE("price"),
    QUANTITY("quantity"),
    PROMOTION("promotion");

    private final String key;

    ProductFields(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
