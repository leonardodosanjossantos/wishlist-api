package io.github.leonardosantos.wishlist.domain.gateways;

import io.github.leonardosantos.wishlist.domain.entities.Wishlist;

public interface WishlistGateway {
    void save(Wishlist wishlist);

    Wishlist get(String customerId);
}