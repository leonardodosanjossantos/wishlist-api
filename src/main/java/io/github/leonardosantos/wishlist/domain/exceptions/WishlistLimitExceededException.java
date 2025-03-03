package io.github.leonardosantos.wishlist.domain.exceptions;

public class WishlistLimitExceededException extends RuntimeException {
    public WishlistLimitExceededException(String message) {
        super(message);
    }
}
