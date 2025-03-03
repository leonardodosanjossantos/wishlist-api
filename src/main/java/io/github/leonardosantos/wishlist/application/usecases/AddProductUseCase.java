package io.github.leonardosantos.wishlist.application.usecases;

import io.github.leonardosantos.wishlist.domain.entities.Product;

public interface AddProductUseCase {

    void execute(String customerId, Product product);

}
