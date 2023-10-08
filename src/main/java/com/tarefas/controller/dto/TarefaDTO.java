package com.tarefas.controller.dto;

import com.tarefas.model.Tarefa;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TarefaDTO {

    private Long id;
    private String titulo;

    public TarefaDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
    }

    public static List<TarefaDTO> converter(List<Tarefa> tarefas) {

        return tarefas.stream().map(TarefaDTO::new).collect(Collectors.toList());
    }
}
