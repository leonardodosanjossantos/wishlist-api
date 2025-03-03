package io.github.leonardosantos.wishlist.infrastructure.gateways;


import io.github.leonardosantos.wishlist.application.dtos.WishlistDTO;
import io.github.leonardosantos.wishlist.domain.entities.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);

    WishlistDTO toWishlistDTO(Wishlist wishlist);
}