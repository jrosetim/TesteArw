package com.jrosetim.testearw.service;

import com.jrosetim.testearw.model.PessoaContatoModel;

import java.util.List;
import java.util.Optional;

public interface PessoaContatoService {

    PessoaContatoModel salvar(PessoaContatoModel pessoaContatoModel);

    PessoaContatoModel editar(PessoaContatoModel pessoaContatoModel);

    void deletar(PessoaContatoModel pessoaContatoModel);

    Optional<PessoaContatoModel> contatosPorPessoa(Long pessoaId);

    List<PessoaContatoModel> getAll();

//    void deletarPorPessoa(Long pessoaId);
}
