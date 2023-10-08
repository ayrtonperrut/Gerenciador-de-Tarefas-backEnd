package com.tarefas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Item {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @JoinColumn(name = "tarefa_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long tarefaId;

    @Getter
    @Setter
    @Column(name="nome")
    private String nome;

    @Getter
    @Setter
    @Column(name="tempo")
    private String tempo;
}
