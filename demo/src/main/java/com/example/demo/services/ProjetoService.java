package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dtos.DadosProjetoDTO;
import com.example.demo.dtos.ProjetoDTO;
import com.example.demo.dtos.RegraNegocioException;
import com.example.demo.models.Projeto;
import com.example.demo.repositories.ProjetoRepository;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<DadosProjetoDTO> listarTodos() {
        return projetoRepository.findAll()
                .stream()
                .map(DadosProjetoDTO::new)
                .collect(Collectors.toList());
    }

    public DadosProjetoDTO obterPorId(Integer id) {
        Projeto p = projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado."));
        return new DadosProjetoDTO(p);
    }

    @Transactional
    public Projeto salvar(ProjetoDTO dto) {
        if (dto == null || dto.getDescricao() == null || dto.getDataInicio() == null || dto.getDataFim() == null) {
            throw new RegraNegocioException("Dados do projeto inválidos.");
        }
        Projeto p = Projeto.builder()
                .descricao(dto.getDescricao())
                .dataInicio(dto.getDataInicio())
                .dataFim(dto.getDataFim())
                .build();
        return projetoRepository.save(p);
    }

    @Transactional
    public void remover(Integer id) {
        if (!projetoRepository.existsById(id)) {
            throw new RegraNegocioException("Projeto não encontrado.");
        }
        projetoRepository.deleteById(id);
    }
}
