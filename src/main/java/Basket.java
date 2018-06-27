import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final Clock clock;
    private List<Product> products = new ArrayList<>();
    private String creationDate;

    public Basket(Clock clock) {
        this.clock = clock;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void add(List<Product> productsToAdd) {
        if (creationDate == null) {
            this.creationDate = clock.todayAsddmmyyyyString();
        }
        this.products.addAll(productsToAdd);
    }

    public double total() {
        return this.products
                .stream()
                .mapToDouble(p -> p.price())
                .sum();
    }

    public String creationDate() {
        return this.creationDate;
    }
}
