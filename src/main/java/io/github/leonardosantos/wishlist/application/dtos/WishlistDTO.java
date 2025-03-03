package io.github.leonardosantos.wishlist.application.dtos;

import io.github.leonardosantos.wishlist.domain.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class WishlistDTO {
    private String customerId;
    private List<Product> products;
}