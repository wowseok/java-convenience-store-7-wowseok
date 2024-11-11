package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.product.Product;

public class InputView {

    public static String getOrdersFromUser() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return Console.readLine();
    }


    public static String askPurchase(Product product, int skipCount) {
        OutputView.displayNonFree(product, skipCount);
        return Console.readLine();
    }

    public static String askAddFreeItems(Product product, int extraGiven) {
        OutputView.displayAddItemAlert(product, extraGiven);
        return Console.readLine();
    }

    public static String askMembershipOption() {
        System.out.println("멤버십을 적용하시겠습니까? (Y/N)");
        return Console.readLine();
    }
}
