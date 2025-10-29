package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.DadosSetorDTO;
import com.example.demo.dtos.SetorDTO;
import com.example.demo.dtos.RegraNegocioException;
import com.example.demo.models.Setor;
import com.example.demo.repositories.SetorRepository;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public List<DadosSetorDTO> listarTodos() {
        return setorRepository.findAllWithFuncionarios()
                .stream()
                .map(DadosSetorDTO::new)
                .collect(Collectors.toList());
    }

    public DadosSetorDTO obterPorId(Integer id) {
        Setor s = setorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado."));
        return new DadosSetorDTO(s);
    }

    @Transactional
    public Setor salvar(SetorDTO dto) {
        if (dto == null || dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException("Nome do setor inválido.");
        }
        Setor s = Setor.builder().nome(dto.getNome()).build();
        return setorRepository.save(s);
    }

    @Transactional
    public void remover(Integer id) {
        if (!setorRepository.existsById(id)) {
            throw new RegraNegocioException("Setor não encontrado.");
        }
        setorRepository.deleteById(id);
    }
}
