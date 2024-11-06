package store;

import store.mvc.controller.Maincontroller;
import store.mvc.view.InputView;
import store.mvc.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Maincontroller maincontroller = new Maincontroller(new InputView(), new OutputView());
        maincontroller.run();
    }
}
