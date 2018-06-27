# Shopping Basket Kata
A solution to a Shopping Basket Kata I undertook.
## Requirements
Given I add 2 units of "The Hobbit" to my shopping basket And I add 5 units of "Breaking Bad" When I check the content
of my shopping basket Then it should contain the following information:
* Creation date (of the shopping basket)
* 2 x The Hobbit // 2 x 5.00 = £10.00
* 5 x Breaking Bad // 5 x 7.00 = £35.00
* Total: £45.00

*Acceptance criteria:*
* Shopping basket should be created when the first product is added.
* Shopping basket should be persisted (In-memory, DB later)
* Each user should have her own shopping basket.

The following outlines the class structure the service should have:
```java
public class ShoppingBasketService {
    
    public void addItem(UserID userId, ProductID productId, int quantity) { }

    public <?> basketFor(UserID userId) { }
}
```

## Running the tests
To run the tests, run the following command at the project-root:

    $ ./gradlew test