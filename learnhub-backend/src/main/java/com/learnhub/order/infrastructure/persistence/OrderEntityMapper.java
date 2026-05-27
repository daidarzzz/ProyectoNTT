package com.learnhub.order.infrastructure.persistence;

import com.learnhub.order.domain.Order;
import com.learnhub.order.domain.OrderDetail;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    @Mapping(target = "detalles", ignore = true)
    Order toDomain(OrderEntity entity);

    @Mapping(target = "detalles", ignore = true)
    OrderEntity toEntity(Order domain);

    @Mapping(target = "idPedido", source = "entity.pedido.id")
    OrderDetail toDomain(OrderDetailEntity entity);

    @Mapping(target = "pedido", ignore = true)
    OrderDetailEntity toEntity(OrderDetail domain);

    @AfterMapping
    default void mapDetallesFromEntity(OrderEntity entity, @MappingTarget Order domain) {
        if (entity.getDetalles() != null) {
            domain.setDetalles(entity.getDetalles().stream().map(this::toDomain).toList());
        } else {
            domain.setDetalles(new ArrayList<>());
        }
    }

    @AfterMapping
    default void mapDetallesToEntity(Order domain, @MappingTarget OrderEntity entity) {
        if (domain.getDetalles() != null) {
            entity.setDetalles(domain.getDetalles().stream().map(d -> {
                var detailEntity = toEntity(d);
                detailEntity.setPedido(entity);
                return detailEntity;
            }).toList());
        }
    }
}
