package com.tarefas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Tarefa {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="titulo")
    private String titulo;

    public Tarefa(String titulo) {
        this.titulo = titulo;
    }

    public Tarefa() {

    }
}
