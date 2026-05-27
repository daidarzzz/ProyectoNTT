package com.learnhub.user.infrastructure.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.learnhub.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "id", ignore = true)
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
