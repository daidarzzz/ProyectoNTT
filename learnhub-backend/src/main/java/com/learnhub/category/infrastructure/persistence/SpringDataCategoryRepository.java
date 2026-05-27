package com.learnhub.category.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
