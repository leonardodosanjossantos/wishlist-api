package io.github.leonardosantos.wishlist.application.usecases.impl;

import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.exceptions.WishlistNotFoundException;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;
import io.github.leonardosantos.wishlist.application.usecases.RemoveProductUseCase;

public class RemoveProductUseCaseImpl implements RemoveProductUseCase {

    private final WishlistGateway wishlistGateway;

    public RemoveProductUseCaseImpl(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    @Override
    public void execute(String customerId, String productId) {
        Wishlist wishlist = wishlistGateway.get(customerId);
        if (wishlist == null) {
            throw new WishlistNotFoundException("Wishlist not found for customer: " + customerId);
        }
        wishlist.removeProduct(productId);
        wishlistGateway.save(wishlist);
    }

}
