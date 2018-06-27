import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class InMemoryProductRepositoryShould {

    InMemoryProductRepository productRepository;

    @Before
    public void setUp() {
        Product theHobbit = new Product(new ProductId("10001"), "The Hobbit", 5);
        Product breakingBad = new Product(new ProductId("20110"), "Breaking Bad", 7);
        productRepository = new InMemoryProductRepository(Arrays.asList(theHobbit, breakingBad));
    }

    @Test
    public void getAProductById(  ) {
        Optional<Product> productFound = productRepository.get(new ProductId("10001"));

        assertEquals("The Hobbit", productFound.get().description());
    }

    @Test
    public void returnAnEmptyOptionalIfNoSuchProductExists(  ) {
        Optional<Product> productFound = productRepository.get(new ProductId("no product has this id"));

        assertEquals(Optional.empty(), productFound);
    }
}
