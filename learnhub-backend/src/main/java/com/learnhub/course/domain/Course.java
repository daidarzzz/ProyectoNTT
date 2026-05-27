package com.learnhub.course.domain;

import java.math.BigDecimal;

public class Course {

    private Long id;
    private String titulo;
    private String descripcion;
    private String descripcionLarga;
    private BigDecimal precio;
    private String imagenUrl;
    private Long idCategoria;
    private Integer horas;
    private String autor;

    public Course() {}

    public Course(String titulo, String descripcion, String descripcionLarga, BigDecimal precio,
                  String imagenUrl, Long idCategoria, Integer horas, String autor) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.idCategoria = idCategoria;
        this.horas = horas;
        this.autor = autor;
    }

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
