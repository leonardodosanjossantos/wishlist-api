package io.github.leonardosantos.wishlist.application.usecases;

public interface RemoveProductUseCase {

    void execute(String customerId, String productId);

}
