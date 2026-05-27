package com.learnhub.course.infrastructure.persistence;

import org.mapstruct.Mapper;
import com.learnhub.course.domain.Course;

@Mapper(componentModel = "spring")
public interface CourseEntityMapper {

    Course toDomain(CourseEntity entity);

    CourseEntity toEntity(Course domain);
}
