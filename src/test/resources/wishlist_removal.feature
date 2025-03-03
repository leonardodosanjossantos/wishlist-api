Feature: Remove products from the wishlist

  Scenario: Remove a product from the wishlist
    Given a wishlist exists for customer "123"
    And the product "prod123" is in the wishlist
    When I remove the product "prod123" from the wishlist
    Then the product "prod123" should not be in the wishlist

  Scenario: Try to remove a product that does not exist
    Given a wishlist exists for customer "123"
    When I remove the product "prod123" from the wishlist
    Then an exception "ProductNotFoundException" should be thrown