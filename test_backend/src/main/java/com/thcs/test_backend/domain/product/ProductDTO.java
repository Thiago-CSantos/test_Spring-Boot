package com.thcs.test_backend.domain.product;

public record ProductDTO(String title, String description, Integer price, String categoryId, String ownerId) {
}
