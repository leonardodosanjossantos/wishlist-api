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
public class GetWishlistUseCaseImplTest {

    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private GetWishlistUseCaseImpl getWishlistUseCase;

    private String customerId;
    private Wishlist wishlist;

    @BeforeEach
    void setUp() {
        customerId = "customer-123";
        wishlist = new Wishlist(customerId, List.of(new Product("product-1", "Product 1", new BigDecimal("100.0"))));
    }

    @Test
    void testExecute_WishlistFound() {
        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        Wishlist result = getWishlistUseCase.execute(customerId);

        assertNotNull(result);
        assertEquals(wishlist, result);

        verify(wishlistGateway, times(1)).get(customerId);
    }

    @Test
    void testExecute_WishlistNotFound() {
        when(wishlistGateway.get(customerId)).thenReturn(null);

        WishlistNotFoundException exception = assertThrows(WishlistNotFoundException.class, () -> {
            getWishlistUseCase.execute(customerId);
        });

        assertEquals("Wishlist not found for customer: " + customerId, exception.getMessage());

        verify(wishlistGateway, times(1)).get(customerId);
    }
}