package io.github.leonardosantos.wishlist.application.usecases;

public interface ContainsProductUseCase {

    boolean execute(String customerId, String productId);

}
