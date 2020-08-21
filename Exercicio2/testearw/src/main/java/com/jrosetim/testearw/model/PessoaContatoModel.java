package com.jrosetim.testearw.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoacontato")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaContatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String celular;

    @ManyToOne
    @JoinColumn(name = "pessoaid")
    private PessoaModel pessoa;
}
