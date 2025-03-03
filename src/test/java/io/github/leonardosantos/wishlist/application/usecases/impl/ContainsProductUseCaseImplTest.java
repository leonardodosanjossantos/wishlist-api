package io.github.leonardosantos.wishlist.application.usecases.impl;

import io.github.leonardosantos.wishlist.domain.entities.Product;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.exceptions.WishlistNotFoundException;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContainsProductUseCaseImplTest {

    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private ContainsProductUseCaseImpl containsProductUseCase;

    private String customerId;
    private String productId;
    private Wishlist wishlist;

    @BeforeEach
    void setUp() {
        customerId = "customer-123";
        productId = "product-1";
        wishlist = new Wishlist(customerId, List.of(new Product(productId, "Product 1", new BigDecimal("100.0"))));
    }

    @Test
    void testExecute_ProductFoundInWishlist() {
        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        boolean result = containsProductUseCase.execute(customerId, productId);

        assertTrue(result);
        verify(wishlistGateway, times(1)).get(customerId);
    }

    @Test
    void testExecute_ProductNotFoundInWishlist() {
        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        boolean result = containsProductUseCase.execute(customerId, "non-existent-product");

        assertFalse(result);
        verify(wishlistGateway, times(1)).get(customerId);
    }

    @Test
    void testExecute_WishlistNotFound() {
        when(wishlistGateway.get(customerId)).thenReturn(null);

        WishlistNotFoundException exception = assertThrows(WishlistNotFoundException.class, () -> {
            containsProductUseCase.execute(customerId, productId);
        });

        assertEquals("Wishlist not found for customer: " + customerId, exception.getMessage());
        verify(wishlistGateway, times(1)).get(customerId);
    }
}