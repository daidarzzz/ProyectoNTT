package com.learnhub.review.infrastructure.persistence;

import org.mapstruct.Mapper;
import com.learnhub.review.domain.Review;

@Mapper(componentModel = "spring")
public interface ReviewEntityMapper {

    Review toDomain(ReviewEntity entity);

    ReviewEntity toEntity(Review domain);
}
