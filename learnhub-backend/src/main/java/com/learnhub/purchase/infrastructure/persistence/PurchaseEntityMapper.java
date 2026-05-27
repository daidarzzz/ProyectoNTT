package com.learnhub.purchase.infrastructure.persistence;

import org.mapstruct.Mapper;
import com.learnhub.purchase.domain.Purchase;

@Mapper(componentModel = "spring")
public interface PurchaseEntityMapper {

    Purchase toDomain(PurchaseEntity entity);

    PurchaseEntity toEntity(Purchase domain);
}
