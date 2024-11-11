package store.product;

import java.util.UUID;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotion;
    private final String id;

    public Product(String name, int price, int quantity, String promotion) {
        this.id = UUID.randomUUID().toString(); // UUID로 고유 ID 생성
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getId() {
        return id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

