package io.github.leonardosantos.wishlist.domain.entities;

import io.github.leonardosantos.wishlist.domain.exceptions.ProductAlreadyExistsException;
import io.github.leonardosantos.wishlist.domain.exceptions.WishlistLimitExceededException;
import io.github.leonardosantos.wishlist.domain.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistTest {

    @Test
    public void testAddProduct_Success() {
        Wishlist wishlist = new Wishlist("123");
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));

        wishlist.addProduct(product);

        assertEquals(1, wishlist.getProducts().size());
        assertTrue(wishlist.containsProduct("prod123"));
    }

    @Test
    public void testAddProduct_WishlistLimitExceeded() {
        Wishlist wishlist = new Wishlist("123");
        for (int i = 0; i < 20; i++) {
            wishlist.addProduct(new Product("prod" + i, "Product " + i, new BigDecimal("100.0")));
        }

        Product newProduct = new Product("prod20", "Product 20", new BigDecimal("100.0"));
        assertThrows(WishlistLimitExceededException.class, () -> wishlist.addProduct(newProduct));
    }

    @Test
    public void testAddProduct_ProductAlreadyExists() {
        Wishlist wishlist = new Wishlist("123");
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));

        wishlist.addProduct(product);
        assertThrows(ProductAlreadyExistsException.class, () -> wishlist.addProduct(product));
    }

    @Test
    public void testRemoveProduct_Success() {
        Wishlist wishlist = new Wishlist("123");
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));

        wishlist.addProduct(product);
        wishlist.removeProduct("prod123");

        assertEquals(0, wishlist.getProducts().size());
        assertFalse(wishlist.containsProduct("prod123"));
    }

    @Test
    public void testRemoveProduct_ProductNotFound() {
        Wishlist wishlist = new Wishlist("123");
        assertThrows(ProductNotFoundException.class, () -> wishlist.removeProduct("prod123"));
    }

    @Test
    public void testContainsProduct() {
        Wishlist wishlist = new Wishlist("123");
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));

        wishlist.addProduct(product);
        assertTrue(wishlist.containsProduct("prod123"));
        assertFalse(wishlist.containsProduct("prod456"));
    }
}