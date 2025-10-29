package com.example.demo.models;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Funcionario {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200, nullable = false)
    private String nome;


    
    @ManyToOne
    @JoinColumn(name = "setor_id")
    @ToString.Exclude
    private Setor setor;

     @ManyToMany
     @JoinTable(
         name = "funcionario_projeto",
         joinColumns = @JoinColumn(name = "funcionario_id"),
         inverseJoinColumns = @JoinColumn(name = "projeto_id")
     )
     @ToString.Exclude
     private List<Projeto> projetos;
}