package io.github.leonardosantos.wishlist.infrastructure.configuration;

import io.github.leonardosantos.wishlist.application.usecases.AddProductUseCase;
import io.github.leonardosantos.wishlist.application.usecases.ContainsProductUseCase;
import io.github.leonardosantos.wishlist.application.usecases.GetWishlistUseCase;
import io.github.leonardosantos.wishlist.application.usecases.RemoveProductUseCase;
import io.github.leonardosantos.wishlist.application.usecases.impl.ContainsProductUseCaseImpl;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;
import io.github.leonardosantos.wishlist.application.usecases.impl.AddProductUseCaseImpl;
import io.github.leonardosantos.wishlist.application.usecases.impl.GetWishlistUseCaseImpl;
import io.github.leonardosantos.wishlist.application.usecases.impl.RemoveProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public AddProductUseCase addProductUseCase(WishlistGateway wishlistGateway) {
        return new AddProductUseCaseImpl(wishlistGateway);
    }

    @Bean
    public ContainsProductUseCase containsProductUseCase(WishlistGateway wishlistGateway) {
        return new ContainsProductUseCaseImpl(wishlistGateway);
    }

    @Bean
    public GetWishlistUseCase getWishlistUseCase(WishlistGateway wishlistGateway) {
        return new GetWishlistUseCaseImpl(wishlistGateway);
    }

    @Bean
    public RemoveProductUseCase removeProductUseCase(WishlistGateway wishlistGateway) {
        return new RemoveProductUseCaseImpl(wishlistGateway);
    }

}
