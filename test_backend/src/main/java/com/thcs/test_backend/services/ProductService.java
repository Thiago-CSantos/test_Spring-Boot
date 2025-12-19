package com.thcs.test_backend.services;

import com.thcs.test_backend.domain.category.Category;
import com.thcs.test_backend.domain.product.ProductDTO;
import com.thcs.test_backend.domain.product.exceptions.ProductNotFoundException;
import com.thcs.test_backend.domain.product.Product;
import com.thcs.test_backend.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    private CategoryService categoryService;

    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Product insert(ProductDTO productDTO) {
        Category category = categoryService.getById(productDTO.categoryId());
        Product product = new Product(productDTO);
        product.setCategory(category);

        return repository.insert(product);
    }

    public List<Product> getAll() {
        var products = repository.findAll();
        return repository.findAll();
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if (!productDTO.title().isEmpty()) {
            product.setTitle(productDTO.title());
        }
        if (productDTO.description() != null) {

            if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
        }

        if (!productDTO.categoryId().isEmpty()) {
            Category category = categoryService.getById(productDTO.categoryId());
            product.setCategory(category);
        }

        if (!productDTO.ownerId().isEmpty()) {
            product.setOwnerId(productDTO.ownerId());
        }

        if (productDTO.price() != null) {
            product.setPrice(productDTO.price());
        }

        return repository.save(product);
    }

    public void delete(String id) {
        Product product = repository.findById(id).orElseThrow(ProductNotFoundException::new);

        repository.delete(product);
    }

}
