package io.github.leonardosantos.wishlist.domain.entities;

import io.github.leonardosantos.wishlist.domain.exceptions.ProductAlreadyExistsException;
import io.github.leonardosantos.wishlist.domain.exceptions.ProductNotFoundException;
import io.github.leonardosantos.wishlist.domain.exceptions.WishlistLimitExceededException;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private String customerId;
    private List<Product> products;
    private static final int MAX_PRODUCTS = 20;

    public Wishlist(String customerId) {
        this.customerId = customerId;
        this.products = new ArrayList<>();
    }

    public Wishlist(String customerId, List<Product> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (products.size() >= MAX_PRODUCTS) {
            throw new WishlistLimitExceededException("Wishlist cannot have more than " + MAX_PRODUCTS + " products.");
        }
        if (containsProduct(product.id())) {
            throw new ProductAlreadyExistsException("Product already exists in the wishlist.");
        }
        products.add(product);
    }

    public void removeProduct(String productId) {
        if (!containsProduct(productId)) {
            throw new ProductNotFoundException("Product not found in the wishlist.");
        }
        products.removeIf(product -> product.id().equals(productId));
    }

    public boolean containsProduct(String productId) {
        return products.stream().anyMatch(product -> product.id().equals(productId));
    }
}
