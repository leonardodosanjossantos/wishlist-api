Feature: Consult the customer's wishlist

  Scenario: Get the customer's wishlist
    Given a wishlist exists for customer "123"
    And the product "prod123" is in the wishlist
    And the product "prod456" is in the wishlist
    When I get the wishlist for customer "123"
    Then the wishlist should contain 2 products