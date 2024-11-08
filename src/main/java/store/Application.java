package store;

import store.global.Maincontroller;
import store.product.ProductManager;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Maincontroller maincontroller = new Maincontroller(new InputView(), new OutputView());
        maincontroller.run(new ProductManager());
    }
}
