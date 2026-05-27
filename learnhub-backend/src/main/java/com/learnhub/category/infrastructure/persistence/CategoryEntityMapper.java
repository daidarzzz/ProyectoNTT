package com.learnhub.category.infrastructure.persistence;

import org.mapstruct.Mapper;
import com.learnhub.category.domain.Category;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    Category toDomain(CategoryEntity entity);

    CategoryEntity toEntity(Category domain);
}
