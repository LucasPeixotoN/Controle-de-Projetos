package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.FuncionarioDTO;
import com.example.demo.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionar(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.criarFuncionario(funcionarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listar() {
        return ResponseEntity.ok(funcionarioService.listarFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> obter(@PathVariable Integer id) {
        return ResponseEntity.ok(funcionarioService.obterFuncionarioPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        funcionarioService.removerFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@PathVariable Integer id, @RequestBody FuncionarioDTO dto) {
        funcionarioService.editarFuncionario(id, dto);
        return ResponseEntity.noContent().build();
    }
}
