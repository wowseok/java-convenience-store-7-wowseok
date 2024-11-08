package store.product;


/**
 * Product 클래스는 플라이웨이트 패턴의 내부 상태를 가진 객체입니다.
 * - 내부 상태: 공유 가능한 데이터 (name, price).
 * - 프로모션(promotion)은 내부 상태로 포함되며, 동적으로 변경 가능합니다.
 * <p>
 * 플라이웨이트 패턴의 의도:
 * - 동일한 name, price 값을 가진 객체는 재사용되며, 프로모션 상태는 변경 가능합니다.
 */

public class Product {
    private final String name;
    private final int price;

    // (프로모션 변경 가능)
    private String promotion;

    // Private 생성자
    public Product(String name, int price, String promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
    }


    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    // Getter 메서드들


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPromotion() {
        return promotion;
    }


    public String toString() {
        return String.format("Product{name='%s', price=%d, promotion='%s'}", name, price, promotion);
    }
}

