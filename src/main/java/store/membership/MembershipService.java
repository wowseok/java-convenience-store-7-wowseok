package store.membership;

import java.util.List;
import store.receipt.OrderResult;
import store.receipt.Receipt;

public class MembershipService {

    private static final double MEMBERSHIP_DISCOUNT_RATE = 0.3; // 30% 할인
    private static final int MAX_DISCOUNT = 8000; // 최대 할인 금액

    public Receipt calculateReceipt(List<OrderResult> orderResults, boolean applyMembership) {
        int totalCost = 0;
        int totalDiscountAmount = 0;
        int totalQuantity = 0;
        int discountableAmount = 0;

        for (OrderResult order : orderResults) {
            totalCost += order.getTotalCost();
            totalQuantity += order.getQuantityPurchased();

            // 증정받은 수량과 금액이 0인 경우만 할인 대상
            if (order.getExtraGivenQuantity() == 0 && order.getExtraGivenCost() == 0) {
                discountableAmount += order.getTotalCost();
            } else {
                totalDiscountAmount += order.getExtraGivenCost();
            }
        }

        int membershipDiscount = 0;
        if (applyMembership) {
            membershipDiscount = (int) Math.min(discountableAmount * MEMBERSHIP_DISCOUNT_RATE, MAX_DISCOUNT);
        }

        int finalAmount = totalCost - totalDiscountAmount - membershipDiscount;

        return new Receipt(orderResults, totalCost, totalDiscountAmount, membershipDiscount, finalAmount);
    }
}
