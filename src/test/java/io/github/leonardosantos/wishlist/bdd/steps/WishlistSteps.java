package io.github.leonardosantos.wishlist.bdd.steps;

import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.entities.Product;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.List;

public class WishlistSteps {

    private Wishlist wishlist;
    private Product product;
    private Exception exception;
    private List<Product> retrievedProducts;
    private boolean productInWishlist;

    @Given("a wishlist exists for customer {string}")
    public void aWishlistExistsForCustomer(String customerId) {
        wishlist = new Wishlist(customerId);
    }

    @Given("the product {string} is already in the wishlist")
    public void theProductIsAlreadyInTheWishlist(String productId) {
        product = new Product(productId, "Product", new BigDecimal("100.0"));
        wishlist.addProduct(product);
    }

    @Given("the product {string} is in the wishlist")
    public void theProductIsInTheWishlist(String productId) {
        product = new Product(productId, "Product", new BigDecimal("100.0"));
        wishlist.addProduct(product);
    }

    @When("I add the product {string} to the wishlist")
    public void iAddTheProductToTheWishlist(String productId) {
        try {
            product = new Product(productId, "Product", new BigDecimal("100.0"));
            wishlist.addProduct(product);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("I remove the product {string} from the wishlist")
    public void iRemoveTheProductFromTheWishlist(String productId) {
        try {
            wishlist.removeProduct(productId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("I get the wishlist for customer {string}")
    public void iGetTheWishlistForCustomer(String customerId) {
        retrievedProducts = wishlist.getProducts();
    }

    @When("I check if the product {string} is in the wishlist")
    public void iCheckIfTheProductIsInTheWishlist(String productId) {
        productInWishlist = wishlist.containsProduct(productId);
    }

    @Then("the product {string} should be in the wishlist")
    public void theProductShouldBeInTheWishlist(String productId) {
        Assertions.assertTrue(wishlist.containsProduct(productId));
    }

    @Then("the product {string} should not be in the wishlist")
    public void theProductShouldNotBeInTheWishlist(String productId) {
        Assertions.assertFalse(productInWishlist);
    }

    @Then("an exception {string} should be thrown")
    public void anExceptionShouldBeThrown(String exceptionName) {
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exceptionName, exception.getClass().getSimpleName());
    }

    @Then("the wishlist should contain {int} products")
    public void theWishlistShouldContainProducts(int expectedCount) {
        Assertions.assertEquals(expectedCount, retrievedProducts.size());
    }
}