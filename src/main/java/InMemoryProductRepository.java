import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository{

    HashMap<ProductId, Product> products = new HashMap<>();

    public InMemoryProductRepository(List<Product> productsToAdd) {
        productsToAdd.stream().forEach(p -> this.products.put(p.id(), p));
    }

    public Optional<Product> get(ProductId productId) {
        return products.containsKey(productId) ? Optional.of(products.get(productId)) : Optional.empty();
    }
}
