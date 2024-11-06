package store.mvc.controller;

import store.mvc.view.InputView;
import store.mvc.view.OutputView;

public class Maincontroller {
    private final InputView inputView;
    private final OutputView outputView;

    public Maincontroller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        outputView.printAvailableProducts();
        inputView.getUserInput();
    }
}
