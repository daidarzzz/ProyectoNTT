package com.learnhub.course.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private Long id;
    private String nombre;
    private String descripcion;
    private String descripcionLarga;
    private BigDecimal precio;
    private Long idCategoria;
    private Integer horas;
    private String autor;
    private List<String> imagenes;

    public Course() {
        this.imagenes = new ArrayList<>();
    }

    public Course(String nombre, String descripcion, String descripcionLarga, BigDecimal precio,
                  Long idCategoria, Integer horas, String autor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcionLarga = descripcionLarga;
        this.precio = precio;
        this.idCategoria = idCategoria;
        this.horas = horas;
        this.autor = autor;
        this.imagenes = new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDescripcionLarga() { return descripcionLarga; }
    public void setDescripcionLarga(String descripcionLarga) { this.descripcionLarga = descripcionLarga; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Long getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Long idCategoria) { this.idCategoria = idCategoria; }

    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public List<String> getImagenes() { return imagenes; }
    public void setImagenes(List<String> imagenes) { this.imagenes = imagenes; }
}
