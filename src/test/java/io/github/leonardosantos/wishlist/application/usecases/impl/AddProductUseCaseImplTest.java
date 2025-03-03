package io.github.leonardosantos.wishlist.application.usecases.impl;

import io.github.leonardosantos.wishlist.domain.entities.Product;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.exceptions.WishlistLimitExceededException;
import io.github.leonardosantos.wishlist.domain.exceptions.ProductAlreadyExistsException;
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
public class AddProductUseCaseImplTest {

    @Mock
    private WishlistGateway wishlistGateway;

    @InjectMocks
    private AddProductUseCaseImpl addProductUseCase;

    @Test
    public void testAddProduct_Success() {
        String customerId = "123";
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));
        Wishlist wishlist = new Wishlist(customerId);

        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        addProductUseCase.execute(customerId, product);

        verify(wishlistGateway, times(1)).save(wishlist);
    }

    @Test
    public void testAddProduct_WishlistLimitExceeded() {
        String customerId = "123";
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));
        Wishlist wishlist = new Wishlist(customerId);
        for (int i = 0; i < 20; i++) {
            wishlist.addProduct(new Product("prod" + i, "Product " + i, new BigDecimal("100.0")));
        }

        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        assertThrows(WishlistLimitExceededException.class, () -> {
            addProductUseCase.execute(customerId, product);
        });
    }

    @Test
    public void testAddProduct_ProductAlreadyExists() {
        String customerId = "123";
        Product product = new Product("prod123", "Smartphone", new BigDecimal("999.99"));
        Wishlist wishlist = new Wishlist(customerId);
        wishlist.addProduct(product);

        when(wishlistGateway.get(customerId)).thenReturn(wishlist);

        assertThrows(ProductAlreadyExistsException.class, () -> {
            addProductUseCase.execute(customerId, product);
        });
    }
}