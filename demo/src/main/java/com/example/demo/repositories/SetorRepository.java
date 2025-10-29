package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {
    
    @Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios")
    List<Setor> findAllWithFuncionarios();
}
