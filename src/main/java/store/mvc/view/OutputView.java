package store.mvc.view;

import store.mvc.utility.FileReaderUtil;


public class OutputView {
    public static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";


    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printAvailableProducts() {
        System.out.println(FileReaderUtil.readFile("src/main/resources/products.md"));
    }

}
