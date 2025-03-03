Feature: Add products to the wishlist

  Scenario: Add a product to the wishlist
    Given a wishlist exists for customer "123"
    When I add the product "prod123" to the wishlist
    Then the product "prod123" should be in the wishlist

  Scenario: Try to add a product that already exists
    Given a wishlist exists for customer "123"
    And the product "prod123" is already in the wishlist
    When I add the product "prod123" to the wishlist
    Then an exception "ProductAlreadyExistsException" should be thrown