package io.github.leonardosantos.wishlist.infrastructure.controllers;

import io.github.leonardosantos.wishlist.application.usecases.ContainsProductUseCase;
import io.github.leonardosantos.wishlist.domain.entities.Product;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import io.github.leonardosantos.wishlist.application.usecases.AddProductUseCase;
import io.github.leonardosantos.wishlist.application.usecases.GetWishlistUseCase;
import io.github.leonardosantos.wishlist.application.usecases.RemoveProductUseCase;
import io.github.leonardosantos.wishlist.application.dtos.WishlistDTO;
import io.github.leonardosantos.wishlist.infrastructure.gateways.WishlistMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController implements WishlistControllerDoc {

    private final AddProductUseCase addProductUseCase;
    private final ContainsProductUseCase containsProductUseCase;
    private final GetWishlistUseCase getWishlistUseCase;
    private final RemoveProductUseCase removeProductUseCase;
    private final WishlistMapper wishlistMapper;

    @Override
    @PostMapping("/{customerId}/add")
    public void addProductToWishlist(@PathVariable String customerId, @RequestBody Product product) {
        addProductUseCase.execute(customerId, product);
    }

    @Override
    @DeleteMapping("/{customerId}/remove")
    public void removeProductFromWishlist(@PathVariable String customerId, @RequestParam String productId) {
        removeProductUseCase.execute(customerId, productId);
    }

    @Override
    @GetMapping("/{customerId}")
    public WishlistDTO getWishlist(@PathVariable String customerId) {
        Wishlist wishlist = getWishlistUseCase.execute(customerId);
        return wishlistMapper.toWishlistDTO(wishlist);
    }

    @Override
    @GetMapping("/check/{customerId}/{productId}")
    public ResponseEntity<Boolean> verifyProduct(@PathVariable String customerId, @PathVariable String productId) {
        Boolean result = containsProductUseCase.execute(customerId, productId);
        return ResponseEntity.ok(result);
    }
}
