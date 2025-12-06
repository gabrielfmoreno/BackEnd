package com.example.final_frame.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    public Post() {}

    public Post(String titulo, String conteudo, Usuario autor) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.autor = autor;
    }
}
