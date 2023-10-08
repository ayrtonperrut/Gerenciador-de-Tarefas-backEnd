package com.tarefas.controller.form;

import com.tarefas.model.Tarefa;
import com.tarefas.repository.TarefaRepository;
import lombok.Getter;
import lombok.Setter;

public class AtualizacaoTarefaForm {

    @Setter
    @Getter
    String titulo;

    public Tarefa atualizar(Long id, TarefaRepository tarefaRepository) {

        Tarefa tarefa = tarefaRepository.getReferenceById(id);
        tarefa.setTitulo(this.titulo);

        return tarefa;
    }
}
