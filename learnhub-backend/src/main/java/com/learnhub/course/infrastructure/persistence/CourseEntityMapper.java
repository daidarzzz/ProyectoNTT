package com.learnhub.course.infrastructure.persistence;

import com.learnhub.course.domain.Course;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface CourseEntityMapper {

    @Mapping(target = "imagenes", ignore = true)
    Course toDomain(CourseEntity entity);

    @Mapping(target = "imagenes", ignore = true)
    CourseEntity toEntity(Course domain);

    @AfterMapping
    default void mapImagenes(CourseEntity entity, @MappingTarget Course domain) {
        if (entity.getImagenes() != null) {
            domain.setImagenes(entity.getImagenes().stream().map(CourseImageEntity::getUrl).toList());
        } else {
            domain.setImagenes(new ArrayList<>());
        }
    }

    @AfterMapping
    default void mapImagenes(Course domain, @MappingTarget CourseEntity entity) {
        if (domain.getImagenes() != null) {
            entity.setImagenes(domain.getImagenes().stream().map(url -> {
                var imgEntity = new CourseImageEntity();
                imgEntity.setUrl(url);
                imgEntity.setCurso(entity);
                return imgEntity;
            }).toList());
        }
    }
}
