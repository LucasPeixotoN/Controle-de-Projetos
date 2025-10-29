package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.FuncionarioDTO;

public interface FuncionarioService { 
    void criarFuncionario(FuncionarioDTO funcionarioDTO);

    FuncionarioDTO obterFuncionarioPorId(Integer id);

    void removerFuncionario(Integer id);

    void editarFuncionario(Integer id, FuncionarioDTO funcionarioDTO);

    List<FuncionarioDTO> listarFuncionarios();
}
