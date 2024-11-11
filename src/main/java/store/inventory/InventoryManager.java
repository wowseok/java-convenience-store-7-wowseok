package store.inventory;

import java.util.ArrayList;
import java.util.List;
import store.product.Product;
import store.promotion.Promotion;
import store.promotion.PromotionRegistry;
import store.receipt.OrderResult;
import store.view.InputView;
import store.view.OutputView;

public class InventoryManager {
    private final Inventory inventory;
    private final PromotionRegistry promotionRegistry;
    private final List<OrderResult> orderResults = new ArrayList<>();


    public InventoryManager(Inventory inventory, PromotionRegistry promotionRegistry) {
        this.inventory = inventory;
        this.promotionRegistry = promotionRegistry;
    }

    public boolean processOrder(String productName, int quantity) {
        List<Product> matchingProducts = inventory.findProductsByName(productName);
        if (matchingProducts.isEmpty()) {
            return handleMissingProduct(productName);
        }

        int totalStock = inventory.calculateTotalStock(matchingProducts);
        if (quantity > totalStock) {
            return handleInsufficientStock(productName);
        }

        quantity = consumePromotionStock(matchingProducts, quantity);
        quantity = consumeGeneralStock(matchingProducts, quantity);
        OrderResults(orderResults);
        return handleRemainingQuantity(productName, quantity);
    }

    private int consumePromotionStock(List<Product> products, int quantity) {
        int initQuantity = quantity;
        for (Product product : products) {
            Promotion promotion = promotionRegistry.getPromotionByName(
                    product.getPromotion()); // PromotionRegistry에서 조회

            if (!isPromotionValid(promotion)) {
                continue;
            }
            int promotableSets =
                    product.getQuantity() / (promotion.getBuy() + promotion.getGet()); // 현재 재고로 프로모션 세트를 얼마나 만들 수 있는지
            int promotableQuantity = promotableSets * promotion.getBuy(); // 프로모션 조건에 따라 실제로 구매해야 하는 수량
            int toConsume = Math.min(quantity, //사용자가 요청한 수량(quantity)
                    promotableQuantity);        // 프로모션으로 소비 가능한 최대 수량(promotableQuantity)
            // 프로모션 적용된 총 수량 (구매한 것 + 무료 제공된 것)
            int appliedQuantity = promotableSets * (promotion.getBuy() + promotion.getGet());
            int extraGiven = promotableSets * promotion.getGet(); // 초과 증정 수량 계산
            int skipCount = quantity - appliedQuantity;
            int promotionalReduction = (toConsume / promotion.getBuy() * promotion.getGet());

            quantity = skipNonPromotionItems(quantity, product, skipCount);

            promotionalReduction = choiseAddItems(product, skipCount, extraGiven, promotionalReduction);

            product.setQuantity(
                    product.getQuantity() - toConsume - promotionalReduction);
            quantity -= toConsume;

            if (quantity > 0 && product.getQuantity() > 0) {
                int additional = Math.min(quantity, product.getQuantity());
                product.setQuantity(product.getQuantity() - additional);
                quantity -= additional;
            }
            saveOrderResult(product.getName(), initQuantity, product.getPrice() * initQuantity, extraGiven,
                    extraGiven * product.getPrice());
        }

        return quantity;
    }

    private int choiseAddItems(Product product, int skipCount, int extraGiven, int promotionalReduction) {
        if (skipCount <= 0 && extraGiven > 0) {
            String response = promptAddFreeItems(product, extraGiven);
            if (response.equals("N")) {
                promotionalReduction = 0;
            }
        }
        return promotionalReduction;
    }

    private int skipNonPromotionItems(int quantity, Product product, int skipCount) {
        if (skipCount > 0) {
            String response = promptUnavailableFreeItems(product, skipCount);
            if (response.equals("N")) {
                quantity = quantity - skipCount;
            }
        }
        return quantity;
    }


    private int consumeGeneralStock(List<Product> products, int quantity) {
        int initQuantity = quantity;
        for (Product product : products) {
            if (product.getPromotion() != null) {
                continue;
            }

            int toConsume = Math.min(product.getQuantity(), quantity);
            product.setQuantity(product.getQuantity() - toConsume);
            quantity -= toConsume;

            saveOrderResult(product.getName(), initQuantity, product.getPrice() * initQuantity, 0, 0);

            if (quantity == 0) {
                break;
            }
        }
        return quantity;
    }

    private boolean isPromotionValid(Promotion promotion) {
        if (promotion == null) {
            return false;
        }
        return promotion.isPromotionValid();
    }

    private boolean handleMissingProduct(String productName) {
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");

    }

    private boolean handleInsufficientStock(String productName) {
        throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
    }

    private boolean handleRemainingQuantity(String productName, int quantity) {
        if (quantity > 0) {
            return handleInsufficientStock(productName);
        }
        return true;
    }

    public String promptUnavailableFreeItems(Product product, int skipCount) {
        try {
            String response = InputView.askPurchase(product, skipCount); // 사용자 입력 요청
            if (!response.equals("Y") && !response.equals("N")) {
                throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
            }
            return response; // 올바른 입력을 받은 경우 반환
        } catch (IllegalArgumentException e) {
            return promptUnavailableFreeItems(product, skipCount);
        }

    }

    public String promptAddFreeItems(Product product, int extraGiven) {
        try {
            String response = InputView.askAddFreeItems(product, extraGiven); // 사용자 입력 요청
            if (!response.equals("Y") && !response.equals("N")) {
                throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
            }
            return response; // 올바른 입력을 받은 경우 반환
        } catch (IllegalArgumentException e) {
            return promptAddFreeItems(product, extraGiven);
        }

    }

    private void saveOrderResult(String productName, int purchasedQuantity, int Price, int extraGiven,
                                 int extraGivenCost) {
        // **중복 체크**
        for (OrderResult result : orderResults) {
            if (result.getProductName().equals(productName)) {
                // 기존 주문 데이터 업데이트
                result.updateOrder(purchasedQuantity, Price, extraGiven, extraGivenCost);
                return; // 중복 발견 시 종료
            }
        }
        // **중복되지 않으면 새로 추가**

        OrderResult result = new OrderResult(productName, purchasedQuantity, Price, extraGiven, extraGivenCost);
        orderResults.add(result);
    }


    public static void OrderResults(List<OrderResult> orderResults) {
        OutputView.displayOrderResults(orderResults);
    }

    public List<OrderResult> getOrderResults() {
        return new ArrayList<>(orderResults); // 방어적 복사
    }

}
