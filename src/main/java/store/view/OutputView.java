package store.view;

import java.text.DecimalFormat;
import java.util.List;
import store.product.Product;
import store.receipt.OrderResult;
import store.receipt.Receipt;

public class OutputView {

    public static void displayInventoryHeader() {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();
    }

    // 상품 목록 출력
    public static void displayProducts(List<Product> products) {
        DecimalFormat formatter = new DecimalFormat("#,###"); // 3자리 콤마 포맷

        for (Product product : products) {
            StringBuilder display = new StringBuilder();

            // 상품 기본 정보
            display.append("- ")
                    .append(product.getName())
                    .append(" ")
                    .append(formatter.format(product.getPrice())) // 가격에 콤마 추가
                    .append("원 ");

            // 재고 상태
            if (product.getQuantity() > 0) {
                display.append(product.getQuantity()).append("개");
            } else {
                display.append("재고 없음");
            }

            // 프로모션 정보
            if (product.getPromotion() != null && !product.getPromotion().isEmpty()) {
                display.append(" ").append(product.getPromotion());
            }

            // 출력
            System.out.println(display.toString());
        }
        System.out.println();
    }

    public static void displayNonFree(Product product, int skipCount) {
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n",
                product.getName(), skipCount);
    }

    public static void displayAddItemAlert(Product product, int extraGiven) {
        System.out.printf("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n",
                product.getName(), extraGiven);
    }

    public static void displayOrderResults(List<OrderResult> orderResults) {

        System.out.println("=== 주문 결과 ===");
        for (
                OrderResult result : orderResults) {
            System.out.println(result);
        }
    }

    public static void displayReceipt(Receipt receipt) {
        System.out.println(receipt.toString());
    }


}
