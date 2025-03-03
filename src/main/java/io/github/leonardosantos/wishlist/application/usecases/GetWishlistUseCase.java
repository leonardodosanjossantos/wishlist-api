package io.github.leonardosantos.wishlist.application.usecases;

import io.github.leonardosantos.wishlist.domain.entities.Wishlist;

public interface GetWishlistUseCase {

    Wishlist execute(String customerId);

}
