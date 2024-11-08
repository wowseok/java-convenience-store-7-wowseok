package store.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import store.global.utility.FileReaderUtil;
import store.global.utility.StringParser;
import store.inventory.ProductOutputView;
import store.product.ProductDTO;
import store.product.ProductManager;
import store.product.ProductMapper;
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

        String fileContent = FileReaderUtil.readFile("src/main/resources/products.md");
        List<Map<String, String>> parsedContent = StringParser.parse(fileContent);
        List<ProductDTO> products = new ArrayList<>();
        for (Map<String, String> row : parsedContent) {
            products.add(ProductMapper.mapToProduct(row));
        }

        manager.registerProducts(products);
        ProductOutputView.displayProducts();
        inputView.getUserInput();
    }
}
