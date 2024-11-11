package store.global;

import java.util.List;
import store.global.util.FileReader;
import store.global.util.InputParser;
import store.global.util.InputParser.ItemOrder;
import store.global.util.ProductDataProcessor;
import store.global.util.PromotionDataProcessor;
import store.inventory.Inventory;
import store.inventory.InventoryManager;
import store.membership.MembershipService;
import store.product.Product;
import store.promotion.Promotion;
import store.promotion.PromotionRegistry;
import store.receipt.OrderResult;
import store.receipt.Receipt;
import store.view.InputView;
import store.view.OutputView;

public class Maincontroller {


    public Maincontroller() {
    }


    public void run() {
        if (checkFileReadStatus()) {
            return;  // 파일 읽기 실패하면 처리 중단
        }

        List<Product> products = getProducts();
        List<Promotion> promotions = getPromotions();
        Inventory inventory = new Inventory(products);
        PromotionRegistry registry = new PromotionRegistry(promotions);
        InventoryManager manager = new InventoryManager(inventory, registry);

        inventory.addGeneralProducts(); // 일반 상품 추가

        OutputView.displayInventoryHeader(); // 헤더 출력
        OutputView.displayProducts(inventory.getProducts()); // 상품 목록 출력

        tryPurchaseLogic(inventory, manager);

        List<OrderResult> orderResults = manager.getOrderResults();

        // 멤버십 적용 여부 확인
        String membershipResponse = InputView.askMembershipOption(); // "멤버십을 적용하시겠습니까? (Y/N)"
        boolean applyMembership = membershipResponse.equalsIgnoreCase("Y");

        MembershipService membershipService = new MembershipService();
        Receipt receipt = membershipService.calculateReceipt(orderResults, applyMembership);
        OutputView.displayReceipt(receipt);
        OutputView.displayInventoryHeader(); // 헤더 출력
        OutputView.displayProducts(inventory.getProducts()); // 상품 목록 출력

    }

    private static void tryPurchaseLogic(Inventory inventory, InventoryManager manager) {
        try {
            purchaseLogic(inventory, manager);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            purchaseLogic(inventory, manager);
        }
    }

    private static void purchaseLogic(Inventory inventory, InventoryManager manager) {
        List<ItemOrder> orders = validOrders(inventory);
        for (ItemOrder order : orders) {
            manager.processOrder(order.getName(), order.getQuantity());
        }
    }

    private static List<ItemOrder> validOrders(Inventory inventory) {

        String input = InputView.getOrdersFromUser();
        List<ItemOrder> orders = InputParser.parseInput(input);
        return orders;
    }

    private static List<Promotion> getPromotions() {
        List<Promotion> promotions = PromotionDataProcessor.processPromotionData(
                FileReader.readFile("src/main/resources/promotions.md"));
        return promotions;
    }

    private static List<Product> getProducts() {
        List<Product> products = ProductDataProcessor.processData(
                FileReader.readFile("src/main/resources/products.md"));
        return products;
    }

    private static boolean checkFileReadStatus() {
        return FileReader.readFile("src/main/resources/products.md") == null;
    }


}
