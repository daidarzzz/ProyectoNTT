package com.learnhub.user.infrastructure.persistence;

import com.learnhub.user.domain.User;
import com.learnhub.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository springRepo;
    private final UserEntityMapper mapper;

    public JpaUserRepository(SpringDataUserRepository springRepo, UserEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        var entity = mapper.toEntity(user);
        var saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springRepo.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springRepo.existsByEmail(email);
    }
}
