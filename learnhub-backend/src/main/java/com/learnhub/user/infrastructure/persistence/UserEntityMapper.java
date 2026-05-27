package com.learnhub.user.infrastructure.persistence;

import com.learnhub.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
