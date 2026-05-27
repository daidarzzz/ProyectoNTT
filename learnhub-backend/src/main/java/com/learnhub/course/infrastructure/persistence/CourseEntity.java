package com.learnhub.course.infrastructure.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cursos")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "descripcion_larga", columnDefinition = "TEXT")
    private String descripcionLarga;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    @Column(nullable = false)
    private Integer horas;

    @Column(nullable = false)
    private String autor;

    public CourseEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDescripcionLarga() { return descripcionLarga; }
    public void setDescripcionLarga(String descripcionLarga) { this.descripcionLarga = descripcionLarga; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
}
