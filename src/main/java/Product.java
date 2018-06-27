public class Product {
    private final ProductId id;
    private final String description;
    private final double price;

    public Product(ProductId id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public ProductId id() {
        return id;
    }

    public String description() {
        return this.description;
    }

    public double price() {
        return this.price;
    }
}
