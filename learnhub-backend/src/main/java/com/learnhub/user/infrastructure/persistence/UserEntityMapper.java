package com.learnhub.user.infrastructure.persistence;

import org.mapstruct.Mapper;
import com.learnhub.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
