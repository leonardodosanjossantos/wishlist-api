package io.github.leonardosantos.wishlist.infrastructure.repository;

import io.github.leonardosantos.wishlist.domain.entities.Product;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "wishlist")
public class WishlistEntity {

    @Id
    private String customerId;

    @Field("products")
    private List<Product> products;

}
