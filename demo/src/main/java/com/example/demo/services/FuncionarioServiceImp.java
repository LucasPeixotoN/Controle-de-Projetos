package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.FuncionarioDTO;
import com.example.demo.dtos.RegraNegocioException;
import com.example.demo.models.Funcionario;
import com.example.demo.repositories.FuncionarioRepository;

@Service
public class FuncionarioServiceImp implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImp(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public void criarFuncionario(FuncionarioDTO funcionarioDTO) {
        if (funcionarioDTO == null || funcionarioDTO.getNome() == null || funcionarioDTO.getNome().isBlank()) {
            throw new RegraNegocioException("Nome inválido.");
        }

        Funcionario f = Funcionario.builder()
                .nome(funcionarioDTO.getNome())
                .build();

        funcionarioRepository.save(f);
    }

    @Override
    public FuncionarioDTO obterFuncionarioPorId(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id inválido.");
        }

        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado."));

        return toDTO(f);
    }

    @Override
    @Transactional
    public void removerFuncionario(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id inválido.");
        }
        if (!funcionarioRepository.existsById(id)) {
            throw new RegraNegocioException("Funcionário não encontrado.");
        }
        funcionarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editarFuncionario(Integer id, FuncionarioDTO funcionarioDTO) {
        if (id == null || funcionarioDTO == null) {
            throw new RegraNegocioException("Id ou funcionário inválido.");
        }

        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado."));

        if (funcionarioDTO.getNome() != null) {
            f.setNome(funcionarioDTO.getNome());
        }

        funcionarioRepository.save(f);
    }

    @Override
    public List<FuncionarioDTO> listarFuncionarios() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private FuncionarioDTO toDTO(Funcionario f) {
        return FuncionarioDTO.builder()
                .id(f.getId())
                .nome(f.getNome())
                .build();
    }
}