package com.learnhub.category.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query("SELECT c FROM CategoryEntity c WHERE c.deleted = false")
    List<CategoryEntity> findAllActive();

    @Query("SELECT c FROM CategoryEntity c WHERE c.id = ?1 AND c.deleted = false")
    Optional<CategoryEntity> findByIdActive(Long id);
}
