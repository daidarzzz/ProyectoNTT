package com.learnhub.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAll();
    List<User> findAllIncludingDeleted();
    Optional<User> findByIdIncludingDeleted(Long id);
    Optional<User> findByEmailIncludingDeleted(String email);
    void hardDeleteById(Long id);
    void softDeleteById(Long id);
    void restoreById(Long id);
}
