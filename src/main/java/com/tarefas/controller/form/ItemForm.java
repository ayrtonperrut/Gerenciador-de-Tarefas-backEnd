package com.tarefas.controller.form;

import com.tarefas.model.Item;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NotNull
public class ItemForm {

    private Long tarefa_id;
    private String nome;
    private String tempo;

    public Item converter() {
        return new Item(tarefa_id, nome, tempo);
    }
}
