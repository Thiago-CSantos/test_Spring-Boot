package com.thcs.test_backend.services;

import com.thcs.test_backend.domain.category.Category;
import com.thcs.test_backend.domain.category.CategoryDTO;
import com.thcs.test_backend.domain.category.exceptions.CategoryNotFoundException;
import com.thcs.test_backend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category insert(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        return repository.insert(category);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryDTO.title().isEmpty()) {
            category.setTitle(categoryDTO.title());
        }
        if (!categoryDTO.description().isEmpty()) {
            category.setDescription(categoryDTO.description());
        }

        return repository.save(category);
    }

    public void delete(String id) {
        Category category = repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        repository.delete(category);
    }

    public Category getById(String id) {
        return repository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

}
