package com.jrosetim.testearw.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PessoaContatoDTO {

    private Long id;

    private String nome;

    private String telefone;

    private String celular;

    private Long pessoaid;
}
