package com.tarefas.controller.form;

import com.tarefas.model.Tarefa;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class TarefaForm {

    @Setter
    @Getter
    @NotNull
    private String titulo;

    public Tarefa converter() {
        return new Tarefa(titulo);
    }
}
