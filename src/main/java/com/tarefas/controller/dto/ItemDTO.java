package com.tarefas.controller.dto;

import com.tarefas.model.Item;
import com.tarefas.model.Tarefa;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ItemDTO {

    private Long id;
    private Long tarefa_id;
    private String nome;
    private String tempo;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.tarefa_id = item.getTarefaId();
        this.nome = item.getNome();
        this.tempo = item.getTempo();
    }

    public static List<ItemDTO> converter(List<Item> itens) {

        return itens.stream().map(ItemDTO::new).collect(Collectors.toList());
    }

}
