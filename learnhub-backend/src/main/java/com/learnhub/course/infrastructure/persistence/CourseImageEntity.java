package com.learnhub.course.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "curso_imagenes")
public class CourseImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private CourseEntity curso;

    @Column(nullable = false)
    private String url;

    public CourseImageEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CourseEntity getCurso() { return curso; }
    public void setCurso(CourseEntity curso) { this.curso = curso; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
