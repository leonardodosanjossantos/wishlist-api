package io.github.leonardosantos.wishlist.application.usecases.impl;

import io.github.leonardosantos.wishlist.application.usecases.AddProductUseCase;
import io.github.leonardosantos.wishlist.domain.entities.Product;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;

public class AddProductUseCaseImpl implements AddProductUseCase {

    private final WishlistGateway wishlistGateway;

    public AddProductUseCaseImpl(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    @Override
    public void execute(String customerId, Product product) {
        Wishlist wishlist = wishlistGateway.get(customerId);
        if (wishlist == null) {
            wishlist = new Wishlist(customerId);
        }
        wishlist.addProduct(product);
        wishlistGateway.save(wishlist);
    }

}
