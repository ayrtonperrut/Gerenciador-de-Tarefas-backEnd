package com.tarefas.controller;

import com.tarefas.controller.dto.TarefaDTO;
import com.tarefas.controller.form.AtualizacaoTarefaForm;
import com.tarefas.controller.form.TarefaForm;
import com.tarefas.model.Tarefa;
import com.tarefas.repository.TarefaRepository;
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
@RequestMapping("/tarefa")
public class TarefasController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<TarefaDTO> listar() {

        return  TarefaDTO.converter(tarefaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tarefa>> getById(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        return ResponseEntity.ok(tarefa);
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> novo(@RequestBody @Valid TarefaForm form, UriComponentsBuilder uriBuilder) {

        Tarefa tarefa = form.converter();
        tarefaRepository.save(tarefa);

        URI uri = uriBuilder.path("/tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(new TarefaDTO(tarefa));
    }

    @PutMapping("/{id}")
    @Transactional
    public <T> ResponseEntity<TarefaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTarefaForm form) {

        Optional<Tarefa> optional = tarefaRepository.findById(id);

        if (optional.isPresent()) {
            Tarefa tarefa = form.atualizar(id, tarefaRepository);
            return ResponseEntity.ok(new TarefaDTO(tarefa));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        Optional<Tarefa> optional = tarefaRepository.findById(id);
        if (optional.isPresent()) {
            tarefaRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
