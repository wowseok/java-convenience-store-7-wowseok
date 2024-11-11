package store.receipt;

import java.text.DecimalFormat;
import java.util.List;

public class Receipt {
    private final List<OrderResult> orderResults;
    private final int totalPurchaseAmount;
    private final int totalDiscountAmount;
    private final int membershipDiscount;
    private final int finalAmount;

    private static final DecimalFormat moneyFormat = new DecimalFormat("#,###");

    public Receipt(List<OrderResult> orderResults, int totalPurchaseAmount, int totalDiscountAmount,
                   int membershipDiscount, int finalAmount) {
        this.orderResults = orderResults;
        this.totalPurchaseAmount = totalPurchaseAmount;
        this.totalDiscountAmount = totalDiscountAmount;
        this.membershipDiscount = membershipDiscount;
        this.finalAmount = finalAmount;
    }

    public List<OrderResult> getOrderResults() {
        return orderResults;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public int getMembershipDiscount() {
        return membershipDiscount;
    }

    public int getFinalAmount() {
        return finalAmount;
    }

    private String formatMoney(int amount) {
        return moneyFormat.format(amount) + "원";
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=================W 편의점================\n");
        receipt.append("상품명\t\t수량\t금액\n");

        for (OrderResult result : orderResults) {
            receipt.append(result.getProductName()).append("\t\t")
                    .append(result.getQuantityPurchased()).append("\t")
                    .append(formatMoney(result.getTotalCost())).append("\n");
        }

        receipt.append("=============증 정===============\n");
        for (OrderResult result : orderResults) {
            if (result.getExtraGivenQuantity() > 0) {
                receipt.append(result.getProductName()).append("\t\t")
                        .append(result.getExtraGivenQuantity()).append("\n");
            }
        }

        receipt.append("====================================\n");
        receipt.append("총구매액\t\t").append(orderResults.stream().mapToInt(OrderResult::getQuantityPurchased).sum())
                .append("\t").append(formatMoney(totalPurchaseAmount)).append("\n");
        receipt.append("행사할인\t\t\t-").append(formatMoney(totalDiscountAmount)).append("\n");
        if (membershipDiscount > 0) {
            receipt.append("멤버십할인\t\t\t-").append(formatMoney(membershipDiscount)).append("\n");
        }
        receipt.append("내실돈\t\t\t ").append(formatMoney(finalAmount)).append("\n");

        return receipt.toString();
    }
}
