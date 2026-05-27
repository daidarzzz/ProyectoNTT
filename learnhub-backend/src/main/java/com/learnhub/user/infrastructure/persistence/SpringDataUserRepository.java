package com.learnhub.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 AND u.deleted = false")
    Optional<UserEntity> findByEmailActive(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.deleted = false")
    List<UserEntity> findAllActive();

    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1 AND u.deleted = false")
    Optional<UserEntity> findByIdActive(Long id);

    boolean existsByEmail(String email);
}
