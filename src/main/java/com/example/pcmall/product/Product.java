package com.example.pcmall.product;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(
        Long id,
        String sku,
        String name,
        String brand,
        String category,
        String description,
        BigDecimal price,
        int stock,
        String imageUrl
) {
    public Product {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(sku, "sku must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(brand, "brand must not be null");
        Objects.requireNonNull(category, "category must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(price, "price must not be null");
        Objects.requireNonNull(imageUrl, "imageUrl must not be null");

        if (price.signum() < 0) {
            throw new IllegalArgumentException("price must not be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("stock must not be negative");
        }
    }

    public boolean inStock() {
        return stock > 0;
    }
}
