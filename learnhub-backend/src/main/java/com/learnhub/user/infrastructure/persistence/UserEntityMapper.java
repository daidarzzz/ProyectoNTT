package com.learnhub.user.infrastructure.persistence;

import com.learnhub.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    UserEntity toEntity(User domain);
}
