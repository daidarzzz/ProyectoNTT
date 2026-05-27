package com.learnhub.user.infrastructure.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.learnhub.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(User domain);
}
