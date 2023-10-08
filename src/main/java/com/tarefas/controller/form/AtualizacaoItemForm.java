package com.tarefas.controller.form;

import com.tarefas.model.Item;
import com.tarefas.repository.ItemRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AtualizacaoItemForm {

    Long tarefaId;
    String nome;
    String tempo;

    public Item atualizar(Long id, ItemRepository itemRepository) {

        Item item = itemRepository.getReferenceById(id);
        item.setTarefaId(this.tarefaId);
        item.setNome(this.nome);
        item.setTempo(this.tempo);

        return item;
    }
}
