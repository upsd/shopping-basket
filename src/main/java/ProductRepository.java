import java.util.Optional;

public interface ProductRepository {

    Optional<Product> get(ProductId id);
}
