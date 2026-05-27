package com.learnhub.category.application;

import com.learnhub.category.application.dto.CategoryResponse;
import com.learnhub.category.application.dto.CreateCategoryRequest;
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

    public CategoryResponse create(CreateCategoryRequest request) {
        var category = new Category(request.nombre(), request.descripcion());
        return toResponse(categoryRepository.save(category));
    }

    public CategoryResponse update(Long id, CreateCategoryRequest request) {
        var category = categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));
        category.setNombre(request.nombre());
        category.setDescripcion(request.descripcion());
        return toResponse(categoryRepository.save(category));
    }

    public void delete(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Categoría", id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
            category.getId(),
            category.getNombre(),
            category.getDescripcion()
        );
    }
}
