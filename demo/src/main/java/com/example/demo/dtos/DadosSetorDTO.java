package com.example.demo.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.models.Setor;

public class DadosSetorDTO {

    private Integer id;
    private String nome;
    private List<String> funcionarios;

    public DadosSetorDTO(Setor setor) {
        this.id = setor.getId();
        this.nome = setor.getNome();
        this.funcionarios = setor.getFuncionarios() != null ? setor.getFuncionarios().stream()
                .map(f -> f.getNome())
                .collect(Collectors.toList())
                : null;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getFuncionarios() {
        return funcionarios;
    }
}
