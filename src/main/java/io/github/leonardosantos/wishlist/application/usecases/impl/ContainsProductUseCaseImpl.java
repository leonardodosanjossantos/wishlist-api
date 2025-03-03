package io.github.leonardosantos.wishlist.application.usecases.impl;

import io.github.leonardosantos.wishlist.application.usecases.ContainsProductUseCase;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.exceptions.WishlistNotFoundException;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;

public class ContainsProductUseCaseImpl implements ContainsProductUseCase {

    private final WishlistGateway wishlistGateway;

    public ContainsProductUseCaseImpl(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    @Override
    public boolean execute(String customerId, String productId) {
        Wishlist wishlist = wishlistGateway.get(customerId);
        if (wishlist == null) {
            throw new WishlistNotFoundException("Wishlist not found for customer: " + customerId);
        }
        return wishlist.containsProduct(productId);
    }

}
