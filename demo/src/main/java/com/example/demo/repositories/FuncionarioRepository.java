package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Funcionario;
import com.example.demo.models.Projeto;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    
    
    @Query("SELECT f.projetos FROM Funcionario f WHERE f.id = :idFuncionario")
    List<Projeto> findProjetosByFuncionarioId(@Param("idFuncionario") Integer idFuncionario);
    
    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.projetos WHERE f.id = :id")
    Funcionario findByIdWithProjetos(@Param("id") Integer id);
    
    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.setor WHERE f.id = :id")
    Funcionario findByIdWithSetor(@Param("id") Integer id);
}
