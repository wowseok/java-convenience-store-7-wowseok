package store.global.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final String REGEX = "\\[([가-힣a-zA-Z0-9]+)-([0-9]+)]";

    public static List<ItemOrder> parseInput(String input) {
        if (!isValidInput(input)) {
            //throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }

        return extractOrders(input);
    }

    private static boolean isValidInput(String input) {
        String fullRegex = "^(\\[[가-힣a-zA-Z0-9]+-[0-9]+])(,\\[[가-힣a-zA-Z0-9]+-[0-9]+])*$";
        return Pattern.matches(fullRegex, input);
    }

    private static List<ItemOrder> extractOrders(String input) {
        List<ItemOrder> orders = new ArrayList<>();
        Matcher matcher = Pattern.compile(REGEX).matcher(input);

        while (matcher.find()) {
            orders.add(createOrder(matcher.group(1), matcher.group(2)));
        }

        return orders;
    }

    private static ItemOrder createOrder(String name, String quantity) {
        int parsedQuantity = Integer.parseInt(quantity);
        return new ItemOrder(name, parsedQuantity);
    }

    public static class ItemOrder {
        private final String name;
        private final int quantity;

        public ItemOrder(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "상품명: " + name + ", 수량: " + quantity;
        }
    }
}
