package com.learnhub.category.infrastructure.persistence;

import com.learnhub.category.domain.Category;
import com.learnhub.category.domain.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final SpringDataCategoryRepository springRepo;
    private final CategoryEntityMapper mapper;

    public JpaCategoryRepository(SpringDataCategoryRepository springRepo, CategoryEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public List<Category> findAll() {
        return springRepo.findAllActive().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return springRepo.findByIdActive(id).map(mapper::toDomain);
    }

    @Override
    public Category save(Category category) {
        var entity = mapper.toEntity(category);
        var saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public List<Category> findAllIncludingDeleted() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Category> findByIdIncludingDeleted(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public void hardDeleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public void softDeleteById(Long id) {
        springRepo.findById(id).ifPresent(entity -> {
            entity.setDeleted(true);
            springRepo.save(entity);
        });
    }

    @Override
    public void restoreById(Long id) {
        springRepo.findById(id).ifPresent(entity -> {
            entity.setDeleted(false);
            springRepo.save(entity);
        });
    }
}
