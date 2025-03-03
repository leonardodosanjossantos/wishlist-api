package io.github.leonardosantos.wishlist.application.usecases.impl;

import io.github.leonardosantos.wishlist.application.usecases.GetWishlistUseCase;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.exceptions.WishlistNotFoundException;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;

public class GetWishlistUseCaseImpl implements GetWishlistUseCase {

    private final WishlistGateway wishlistGateway;

    public GetWishlistUseCaseImpl(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    @Override
    public Wishlist execute(String customerId) {
        Wishlist wishlist = wishlistGateway.get(customerId);
        if (wishlist == null) {
            throw new WishlistNotFoundException("Wishlist not found for customer: " + customerId);
        }
        return wishlist;
    }
}
