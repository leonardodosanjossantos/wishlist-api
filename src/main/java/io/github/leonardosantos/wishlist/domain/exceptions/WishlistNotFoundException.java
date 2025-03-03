package io.github.leonardosantos.wishlist.domain.exceptions;

public class WishlistNotFoundException extends RuntimeException {
    public WishlistNotFoundException(String message) {
        super(message);
    }
}
