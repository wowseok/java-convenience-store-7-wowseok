package store.global;

import java.util.List;
import java.util.Map;
import store.global.utility.FileReaderUtil;
import store.global.utility.StringParser;
import store.inventory.ProductView;
import store.product.ProductManager;
import store.view.InputView;
import store.view.OutputView;

public class Maincontroller {
    private final InputView inputView;
    private final OutputView outputView;

    public Maincontroller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(ProductManager manager) {
        outputView.printWelcomeMessage();

        List<Map<String, Object>> data = StringParser.parseFileContent(
                FileReaderUtil.readFile("src/main/resources/products.md"));

        manager.registerProduct(data);
        ProductView.displayProducts();
        //outputView.printAvailableProducts();
        //inputView.getUserInput();
    }
}
