package io.github.leonardosantos.wishlist.infrastructure.controllers;


import io.github.leonardosantos.wishlist.domain.entities.Product;
import io.github.leonardosantos.wishlist.application.dtos.WishlistDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Wishlist", description = "API for managing customer wishlists.")
public interface WishlistControllerDoc {

    @Operation(
            summary = "Add a product to the wishlist",
            description = "Adds a product to a customer's wishlist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Customer not found.")
    })
    void addProductToWishlist(
            @Parameter(description = "ID of the customer", required = true) @PathVariable String customerId,
            @Parameter(description = "Product to be added", required = true) @RequestBody Product product);


    @Operation(
            summary = "Remove a product from the wishlist",
            description = "Removes a product from a customer's wishlist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product removed successfully."),
            @ApiResponse(responseCode = "404", description = "Product not found in the wishlist.")
    })
    void removeProductFromWishlist(
            @Parameter(description = "ID of the customer", required = true) @PathVariable String customerId,
            @Parameter(description = "ID of the product to be removed", required = true) @RequestParam String productId);


    @Operation(
            summary = "Get the customer's wishlist",
            description = "Returns all products in a customer's wishlist."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Wishlist not found.")
    })
    WishlistDTO getWishlist(
            @Parameter(description = "ID of the customer", required = true) @PathVariable String customerId);


    @Operation(
            summary = "Verify an product from the wishlist",
            description = "Returns true or false based if the product exists inside a list."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist retrieved successfully.")
    })
    ResponseEntity<Boolean> verifyProduct(
            @Parameter(description = "ID of the customer", required = true) @PathVariable String customerId,
            @Parameter(description = "ID of the product to be verified", required = true) @RequestParam String productId);
}