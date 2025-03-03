package io.github.leonardosantos.wishlist.infrastructure.gateways;

import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.domain.gateways.WishlistGateway;
import io.github.leonardosantos.wishlist.infrastructure.repository.WishlistEntity;
import io.github.leonardosantos.wishlist.infrastructure.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WishlistRepositoryGateway implements WishlistGateway {

    private final WishlistRepository wishlistRepository;

    private final WishlistMapper wishlistMapper;

    @Override
    public void save(Wishlist wishlist) {
        WishlistEntity wishlistEntity = new WishlistEntity();
        wishlistEntity.setCustomerId(wishlist.getCustomerId());
        wishlistEntity.setProducts(wishlist.getProducts());
        wishlistRepository.save(wishlistEntity);
    }

    @Override
    public Wishlist get(String customerId) {
        var entity = wishlistRepository.findByCustomerId(customerId);
        if(entity != null) {
            return new Wishlist(customerId, entity.getProducts());
        }
        return null;
    }
}
