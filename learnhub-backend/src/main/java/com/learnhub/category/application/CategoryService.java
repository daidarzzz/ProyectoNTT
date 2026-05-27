package com.learnhub.category.application;

import com.learnhub.category.application.dto.CategoryResponse;
import com.learnhub.category.domain.Category;
import com.learnhub.category.domain.CategoryRepository;
import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        var category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));
        return toResponse(category);
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getNombre(),
            category.getDescripcion()
        );
    }
}
