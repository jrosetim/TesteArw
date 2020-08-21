package com.jrosetim.testearw.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PessoaDTO {

    private Long id;
    private String nome;
    private String rg;
    private LocalDate datanascimento;
    private String cpf;
}
