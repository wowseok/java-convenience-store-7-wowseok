package store.inventory;

import java.util.ArrayList;
import java.util.List;
import store.product.Product;

public class Inventory {
    private List<Product> products;

    // 생성자에서 초기 인벤토리 설정
    public Inventory(List<Product> products) {
        // 방어적 복사를 통해 외부에서 리스트가 변경되지 않도록 보호
        this.products = new ArrayList<>(products);
    }

    // 기본 생성자 (필요 시 빈 리스트로 초기화)
    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addGeneralProducts() {
        int size = products.size(); // 기존 리스트 크기 고정

        for (int i = 0; i < size; i++) {
            Product product = products.get(i);
            if (product.getPromotion() != null && !isGeneralProductExists(product.getName())) {
                products.add(GeneralProductFactory.createGeneralProduct(product));
            }
        }
    }

    private boolean isGeneralProductExists(String name) {
        for (Product product : products) {
            if (product.getName().equals(name) && product.getPromotion() == null) {
                return true;
            }
        }
        return false;
    }


    // 모든 상품 목록 반환
    public List<Product> getProducts() {
        return new ArrayList<>(products); // 방어적 복사
    }

    public List<Product> findProductsByName(String productName) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                result.add(product);
            }
        }
        return result;
    }

    public int calculateTotalStock(List<Product> products) {
        int totalStock = 0;
        for (Product product : products) {
            totalStock += product.getQuantity();
        }
        return totalStock;
    }

/*
    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }

    // 상품 제거
    public void removeProduct(String productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    // 특정 이름으로 모든 상품 조회
    public List<Product> getProductsByName(String productName) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                result.add(product);
            }
        }
        return result;
    }

    // 상품의 재고를 감소시키는 메서드
    public void decreaseProductQuantity(Product product, int amount) {
        if (product.getQuantity() < amount) {
            throw new IllegalArgumentException("재고 부족: " + product.getName());
        }
        product.setQuantity(product.getQuantity() - amount);
    }

    // 상품의 재고를 증가시키는 메서드
    public void increaseProductQuantity(Product product, int amount) {
        product.setQuantity(product.getQuantity() + amount);
    }

    // 인벤토리 전체 재고 확인
    public int getTotalQuantity() {
        int total = 0;
        for (Product product : products) {
            total += product.getQuantity();
        }
        return total;
    }
*/

}
