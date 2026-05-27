package com.learnhub.order.infrastructure.persistence;

import com.learnhub.order.domain.Order;
import com.learnhub.order.domain.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepository implements OrderRepository {

    private final SpringDataOrderRepository springRepo;
    private final OrderEntityMapper mapper;

    public JpaOrderRepository(SpringDataOrderRepository springRepo, OrderEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        var entity = mapper.toEntity(order);
        var saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return springRepo.findByIdActive(id).map(mapper::toDomain);
    }

    @Override
    public List<Order> findByUsuarioId(Long usuarioId) {
        return springRepo.findByIdUsuarioActive(usuarioId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Order> findAll() {
        return springRepo.findAllActive().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public List<Order> findAllIncludingDeleted() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Order> findByIdIncludingDeleted(Long id) {
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
