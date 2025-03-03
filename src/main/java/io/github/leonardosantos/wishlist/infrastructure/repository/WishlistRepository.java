package io.github.leonardosantos.wishlist.infrastructure.repository;

import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {
    WishlistEntity findByCustomerId(String customerId);
}
