package store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.product.Product;
import store.product.ProductProvider;

public class ProductTest {

    ProductProvider productProvider = ProductProvider.getInstance();

    @Test
    @DisplayName("객체 생성 후 프로모션 변경 가능")
    void testSetPromotion() {
        // Given
        Product product = productProvider.createProduct("콜라", 1000, "탄산2+1");

        // When
        product.setPromotion("새로운 프로모션");

        // Then
        assertEquals("새로운 프로모션", product.getPromotion(), "프로모션이 변경되어야 함");
    }

    @Test
    @DisplayName("기존 프로모션이 null에서 새로운 값으로 변경 가능")
    void testSetPromotionFromNull() {
        // Given
        Product product = productProvider.createProduct("콜라", 1000, null);

        // When
        product.setPromotion("MD추천상품");

        // Then
        assertEquals("MD추천상품", product.getPromotion(), "프로모션이 null에서 변경되어야 함");
    }

    @Test
    @DisplayName("프로모션이 기존 값에서 null로 변경 가능")
    void testSetPromotionToNull() {
        // Given
        Product product = productProvider.createProduct("콜라", 1000, "탄산2+1");

        // When
        product.setPromotion(null);

        // Then
        assertEquals(null, product.getPromotion(), "프로모션이 null로 변경되어야 함");
    }
}
