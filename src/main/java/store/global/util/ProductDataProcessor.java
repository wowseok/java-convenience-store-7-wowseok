package store.global.util;

import java.util.ArrayList;
import java.util.List;
import store.product.Product;

public class ProductDataProcessor {
    public static List<Product> processData(String data) {
        String[] lines = data.split("\n");
        List<String[]> productData = extractProductData(lines);
        return convertToProducts(productData);
    }

    private static List<String[]> extractProductData(String[] lines) {
        List<String[]> productData = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) { // Skip header
            productData.add(lines[i].split(","));
        }
        return productData;
    }

    private static List<Product> convertToProducts(List<String[]> productData) {
        List<Product> products = new ArrayList<>();
        for (String[] fields : productData) {
            String name = fields[0];
            int price = Integer.parseInt(fields[1]);
            int quantity = Integer.parseInt(fields[2]);
            String promotion = isNull(fields, 3);
            products.add(new Product(name, price, quantity, promotion));
        }
        return products;
    }

    private static String isNull(String[] fields, int index) {
        if ("null".equals(fields[index])) {
            fields[index] = null; // "null" 문자열을 실제 null로 변경
        }
        return fields[index];
    }

}
