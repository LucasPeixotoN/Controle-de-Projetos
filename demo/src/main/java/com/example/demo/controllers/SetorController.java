package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.DadosSetorDTO;
import com.example.demo.dtos.SetorDTO;
import com.example.demo.models.Setor;
import com.example.demo.services.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @GetMapping
    public ResponseEntity<List<DadosSetorDTO>> listar() {
        return ResponseEntity.ok(setorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosSetorDTO> obter(@PathVariable Integer id) {
        return ResponseEntity.ok(setorService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody SetorDTO dto) {
        Setor s = setorService.salvar(dto);
        return ResponseEntity.created(URI.create("/setores/" + s.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        setorService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
