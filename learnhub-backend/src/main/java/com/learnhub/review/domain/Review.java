package com.learnhub.review.domain;

import java.time.LocalDateTime;

public class Review {

    private Long id;
    private Long idUsuario;
    private Long idCurso;
    private Integer puntuacion;
    private String comentario;
    private LocalDateTime fecha;

    public Review() {}

    public Review(Long idUsuario, Long idCurso, Integer puntuacion, String comentario) {
        this.idUsuario = idUsuario;
        this.idCurso = idCurso;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Long getIdCurso() { return idCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }

    public Integer getPuntuacion() { return puntuacion; }
    public void setPuntuacion(Integer puntuacion) { this.puntuacion = puntuacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
