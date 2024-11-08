package store.product;

public enum ProductFormatter {
    NAME {
        @Override
        public String format(Object value) {
            return value.toString();
        }
    },
    PRICE {
        @Override
        public String format(Object value) {
            int price = (int) value;
            return String.format("%,d원", price); // 3자리마다 콤마 추가
        }
    },
    QUANTITY {
        @Override
        public String format(Object value) {
            int quantity = (int) value;
            if (quantity > 0) {
                return quantity + "개";
            }
            return "재고 없음";
        }
    },
    PROMOTION {
        @Override
        public String format(Object value) {
            String promotion = (String) value;
            if (promotion == null || promotion.isEmpty()) {
                return "";
            }
            return promotion;
        }
    };

    public abstract String format(Object value); // 각 ENUM에서 구현할 추상 메서드
}
