package com.example.demo.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO {

    private Integer id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}
