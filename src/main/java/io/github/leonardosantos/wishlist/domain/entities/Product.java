package io.github.leonardosantos.wishlist.domain.entities;

import java.math.BigDecimal;

public record Product(String id, String name, BigDecimal price) {}
