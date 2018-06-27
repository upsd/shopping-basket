import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BasketShould {
    
    @Test
    public void peristProducts(  ) {
        Basket myBasket = new Basket(new Clock());

        List<Product> productsToAdd = asList(new Product(new ProductId("10001"), "Some product", 10));

        myBasket.add(productsToAdd);

        List<Product> productsInBasket = myBasket.getProducts();

        assertEquals(1, productsInBasket.size());
    }

    @Test
    public void recordDateOfBasketBeingCreated() {
        Clock clock = mock(Clock.class);
        Basket myBasket = new Basket(clock);

        given(clock.todayAsddmmyyyyString()).willReturn("27/07/2018");

        List<Product> productsToAdd = asList(new Product(new ProductId("10001"), "Some product", 10));

        myBasket.add(productsToAdd);
        myBasket.add(productsToAdd);

        verify(clock).todayAsddmmyyyyString();
        assertEquals("27/07/2018", myBasket.creationDate());
    }

}