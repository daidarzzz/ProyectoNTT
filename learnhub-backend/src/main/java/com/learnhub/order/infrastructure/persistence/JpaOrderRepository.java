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
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Order> findByUsuarioId(Long usuarioId) {
        return springRepo.findByIdUsuarioOrderByFechaDesc(usuarioId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Order> findAll() {
        return springRepo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}
