import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ShoppingBasketFeature {

    ProductRepository productRepository;
    ShoppingBasketService shoppingBasketService;
    Clock clock = mock(Clock.class);

    Product theHobbit;
    Product breakingBad;

    @Before
    public void setUp() {
        theHobbit = new Product(new ProductId("10001"), "The Hobbit", 5);
        breakingBad = new Product(new ProductId("20110"), "Breaking Bad", 7);
        productRepository = new InMemoryProductRepository(asList(theHobbit, breakingBad));

        shoppingBasketService = new ShoppingBasketService(productRepository, clock);
        given(clock.todayAsddmmyyyyString()).willReturn("28/07/2018", "29/07/2018", "30/07/2018");
    }

    @Test
    public void canAddProductToShoppingBasket(  ) {
        UserId userId = new UserId("1");

        shoppingBasketService.addItem(userId, breakingBad.id(), 2);

        Basket userBasket = shoppingBasketService.basketFor(userId);
        List<Product> matchingProducts = userBasket.getProducts()
                .stream()
                .filter(p -> p.id().equals(breakingBad.id()))
                .collect(Collectors.toList());

        assertEquals(2, matchingProducts.size());
    }

    @Test
    public void cannotAddInvalidProductsToBasket(  ) {
        UserId userId = new UserId("1");
        ProductId productId = new ProductId("this id does not belong to a product");

        shoppingBasketService.addItem(userId, productId, 1);

        Basket userBasket = shoppingBasketService.basketFor(userId);

        assertEquals(0, userBasket.getProducts().size());
    }

    @Test
    public void canCalculateTotalCorrectly(  ) {
        UserId userId = new UserId("1");

        shoppingBasketService.addItem(userId, theHobbit.id(), 2);
        shoppingBasketService.addItem(userId, breakingBad.id(), 5);

        Basket userBasket = shoppingBasketService.basketFor(userId);

        assertEquals(45.00, userBasket.total(), 0);
    }

    @Test
    public void eachUserHasTheirOwnBasket(  ) {
        UserId aUser = new UserId("1");
        UserId aDifferentUser = new UserId("2");

        shoppingBasketService.addItem(aUser, theHobbit.id(), 8);
        shoppingBasketService.addItem(aDifferentUser, breakingBad.id(), 1);

        Basket aUsersBasket = shoppingBasketService.basketFor(aUser);
        Basket aDifferentUsersBasket = shoppingBasketService.basketFor(aDifferentUser);

        assertEquals(40, aUsersBasket.total(), 0);
        assertEquals(7, aDifferentUsersBasket.total(), 0);
    }

    @Test
    public void calculateBasketCreationDateBasedOnWhenFirstItemIsAdded() {
        UserId user1 = new UserId("1");
        UserId user2 = new UserId("2");
        UserId user3 = new UserId("3");

        shoppingBasketService.addItem(user1, theHobbit.id(), 8);
        shoppingBasketService.addItem(user1, breakingBad.id(), 8);
        shoppingBasketService.addItem(user2, theHobbit.id(), 8);
        shoppingBasketService.addItem(user3, theHobbit.id(), 8);

        Basket basket1 = shoppingBasketService.basketFor(user1);
        Basket basket2 = shoppingBasketService.basketFor(user2);
        Basket basket3 = shoppingBasketService.basketFor(user3);

        assertEquals("28/07/2018", basket1.creationDate());
        assertEquals("29/07/2018", basket2.creationDate());
        assertEquals("30/07/2018", basket3.creationDate());
    }
}
