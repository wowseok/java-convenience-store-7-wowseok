package store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.product.Product;
import store.product.ProductFactory;

class ProductFactoryTest {

    @Test
    @DisplayName("동일한 이름, 가격, 프로모션의 객체는 재사용됨")
    void testCreateProductReuse() {
        // Given
        String name = "콜라";
        int price = 1000;
        String promotion = "탄산2+1";

        // When
        Product product1 = ProductFactory.createProduct(name, price, promotion);
        Product product2 = ProductFactory.createProduct(name, price, promotion);

        // Then
        assertSame(product1, product2, "동일한 이름, 가격, 프로모션의 객체는 동일한 인스턴스를 참조해야 함");
    }

    @Test
    @DisplayName("프로모션이 다르면 다른 객체를 생성함")
    void testCreateProductDifferentPromotion() {
        // Given
        String name = "콜라";
        int price = 1000;
        String promotion1 = "탄산2+1";
        String promotion2 = "탄산3+1";

        // When
        Product product1 = ProductFactory.createProduct(name, price, promotion1);
        Product product2 = ProductFactory.createProduct(name, price, promotion2);

        // Then
        assertNotSame(product1, product2, "프로모션이 다르면 서로 다른 객체여야 함");
    }

    @Test
    @DisplayName("이름이나 가격이 다르면 다른 객체를 생성함")
    void testCreateProductDifferentNameOrPrice() {
        // Given
        String name1 = "콜라";
        String name2 = "사이다";
        int price1 = 1000;
        int price2 = 1200;
        String promotion = "탄산2+1";

        // When
        Product product1 = ProductFactory.createProduct(name1, price1, promotion);
        Product product2 = ProductFactory.createProduct(name2, price1, promotion);
        Product product3 = ProductFactory.createProduct(name1, price2, promotion);

        // Then
        assertNotSame(product1, product2, "이름이 다르면 다른 객체여야 함");
        assertNotSame(product1, product3, "가격이 다르면 다른 객체여야 함");
    }

    @Test
    @DisplayName("프로모션이 null이면 처리 가능( 문자열 \"null\"로 입력받아도 자바의 null로 처리")
    void testCreateProductWithNullPromotion() {
        // Given
        String name = "콜라";
        int price = 1000;

        // When
        Product product1 = ProductFactory.createProduct(name, price, null);
        Product product2 = ProductFactory.createProduct(name, price, "null");

        // Then
        assertSame(product1, product2, "프로모션이 null과 'null' 문자열은 동일하게 처리해야 함");
        assertNull(product1.getPromotion(), "프로모션이 null로 처리되어야 함");
        assertNull(product2.getPromotion(), "프로모션이 null로 처리되어야 함");
    }

    @Test
    @DisplayName("객체 생성 후 프로모션 변경 가능")
    void testSetPromotion() {
        // Given
        Product product = ProductFactory.createProduct("콜라", 1000, "탄산2+1");

        // When
        product.setPromotion("새로운 프로모션");

        // Then
        assertEquals("새로운 프로모션", product.getPromotion(), "프로모션이 변경되어야 함");
    }
}
