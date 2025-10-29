package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.DadosProjetoDTO;
import com.example.demo.dtos.ProjetoDTO;
import com.example.demo.models.Projeto;
import com.example.demo.services.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public ResponseEntity<List<DadosProjetoDTO>> listar() {
        return ResponseEntity.ok(projetoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosProjetoDTO> obter(@PathVariable Integer id) {
        return ResponseEntity.ok(projetoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody ProjetoDTO dto) {
        Projeto p = projetoService.salvar(dto);
        return ResponseEntity.created(URI.create("/projetos/" + p.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        projetoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
