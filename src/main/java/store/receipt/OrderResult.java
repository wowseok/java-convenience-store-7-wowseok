package store.receipt;

public class OrderResult {
    private String productName;
    private int quantityPurchased;
    private int totalCost;
    private int extraGivenQuantity;
    private int extraGivenCost;

    public OrderResult(String productName, int quantityPurchased, int totalCost, int extraGivenQuantity,
                       int extraGivenCost) {
        this.productName = productName;
        this.quantityPurchased = quantityPurchased;
        this.totalCost = totalCost;
        this.extraGivenQuantity = extraGivenQuantity;
        this.extraGivenCost = extraGivenCost;
    }

    // 기존 데이터 업데이트
    public void updateOrder(int additionalQuantity, int additionalCost, int additionalExtraGiven,
                            int additionalExtraGivenCost) {
        this.quantityPurchased += additionalQuantity;
        this.totalCost += additionalCost;
        this.extraGivenQuantity += additionalExtraGiven;
        this.extraGivenCost += additionalExtraGivenCost;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getExtraGivenQuantity() {
        return extraGivenQuantity;
    }

    public int getExtraGivenCost() {
        return extraGivenCost;
    }

    @Override
    public String toString() {
        return String.format("상품명: %s, 구매한 수량: %d, 총 금액: %d원, 증정받은 수량: %d, 증정받은 금액: %d원",
                productName, quantityPurchased, totalCost, extraGivenQuantity, extraGivenCost);
    }
}
