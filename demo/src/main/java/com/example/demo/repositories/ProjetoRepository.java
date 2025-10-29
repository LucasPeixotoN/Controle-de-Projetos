package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {


    @Query("select p from Projeto p left join fetch p.funcionarios f where f.id = :idFuncionario")
    List<Projeto> findByIdFuncionario(Integer idFuncionario);

    List<Projeto> findByDataInicioBetween(LocalDate inicio, LocalDate fim);
    
}
