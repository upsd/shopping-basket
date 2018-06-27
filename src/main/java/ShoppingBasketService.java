import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ShoppingBasketService {

    private final ProductRepository productRepository;
    private final HashMap<UserId, Basket> baskets = new HashMap<>();
    private final Clock clock;

    public ShoppingBasketService(ProductRepository productRepository, Clock clock) {
        this.productRepository = productRepository;
        this.clock = clock;
    }

    public void addItem(UserId userId, ProductId productId, int quantity) {
        Basket basket = new Basket(clock);

        if (baskets.containsKey(userId)) {
            basket = baskets.get(userId);
        }

        basket.add(createProductOfQuantity(productId, quantity));
        baskets.put(userId, basket);
    }

    public Basket basketFor(UserId userId) {
        return this.baskets.get(userId);
    }

    private List<Product> createProductOfQuantity(ProductId productId, int chosenQuantity) {
        List<Product> createdProducts = new ArrayList<>();
        Optional<Product> productToCreate = productRepository.get(productId);

        if (!productToCreate.isPresent()) {
            return new ArrayList<>();
        }

        for (int i = 0; i < chosenQuantity; i++) {
            createdProducts.add(productToCreate.get());
        }

        return createdProducts;
    }
}