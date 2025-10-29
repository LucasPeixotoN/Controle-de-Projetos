package com.example.demo.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.models.Projeto;

public class DadosProjetoDTO {

    private Integer id;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private List<String> funcionarios;

    public DadosProjetoDTO(Projeto projeto) {
        this.id = projeto.getId();
        this.descricao = projeto.getDescricao();
        this.dataInicio = projeto.getDataInicio() != null ? projeto.getDataInicio().toString() : null;
        this.dataFim = projeto.getDataFim() != null ? projeto.getDataFim().toString() : null;
        this.funcionarios = projeto.getFuncionarios() != null ? projeto.getFuncionarios().stream()
                .map(f -> f.getNome())
                .collect(Collectors.toList())
                : null;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public List<String> getFuncionarios() {
        return funcionarios;
    }
}
