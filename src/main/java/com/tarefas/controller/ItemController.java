package com.tarefas.controller;

import com.tarefas.controller.dto.ItemDTO;
import com.tarefas.controller.form.AtualizacaoItemForm;
import com.tarefas.controller.form.ItemForm;
import com.tarefas.model.Item;
import com.tarefas.repository.ItemRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Item>> getById(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);

        return ResponseEntity.ok(item);
    }

    @GetMapping("/{tarefaId}/itens")
    public ResponseEntity<List<Item>> getByTarefaId(@PathVariable Long tarefaId) {
        List<Item> itens = itemRepository.getByTarefaId(tarefaId);

        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> novo(@RequestBody @Valid ItemForm form, UriComponentsBuilder uriBuilder) {

        Item item = form.converter();
        itemRepository.save(item);

        URI uri = uriBuilder.path("/item/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new ItemDTO(item));
    }

    @PutMapping("/{id}")
    @Transactional
    public <T> ResponseEntity<ItemDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoItemForm form) {

        Optional<Item> optional = itemRepository.findById(id);

        if (optional.isPresent()) {
            Item item = form.atualizar(id, itemRepository);
            return ResponseEntity.ok(new ItemDTO(item));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        Optional<Item> optional = itemRepository.findById(id);
        if (optional.isPresent()) {
            itemRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
