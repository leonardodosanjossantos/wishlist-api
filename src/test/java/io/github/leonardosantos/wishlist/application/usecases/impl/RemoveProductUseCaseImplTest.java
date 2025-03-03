package io.github.leonardosantos.wishlist.application.usecases.impl;

import io.github.leonardosantos.wishlist.domain.entities.Product;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.exceptions.ProductNotFoundException;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RemoveProductUseCaseImplTest {

    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private RemoveProductUseCaseImpl removeProductUseCase;

    @Test
    public void testRemoveProduct_Success() {
        String customerId = "123";
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));
        Wishlist wishlist = new Wishlist(customerId);
        wishlist.addProduct(product);

        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        removeProductUseCase.execute(customerId, "prod123");

        verify(wishlistGateway, times(1)).save(wishlist);
    }

    @Test
    public void testRemoveProduct_ProductNotFound() {
        String customerId = "123";
        Wishlist wishlist = new Wishlist(customerId);

        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        assertThrows(ProductNotFoundException.class, () -> {
            removeProductUseCase.execute(customerId, "prod123");
        });
    }
}