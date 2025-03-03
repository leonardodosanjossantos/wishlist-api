Feature: Verify if a product is in the wishlist

  Scenario: Check if a product is in the wishlist
    Given a wishlist exists for customer "123"
    And the product "prod123" is in the wishlist
    When I check if the product "prod123" is in the wishlist
    Then the product "prod123" should be in the wishlist
    When I check if the product "prod456" is in the wishlist
    Then the product "prod456" should not be in the wishlist