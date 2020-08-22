package com.jrosetim.testearw.service;

import com.jrosetim.testearw.model.PessoaContatoModel;

import java.util.List;

public interface PessoaContatoService {

    PessoaContatoModel salvar(PessoaContatoModel pessoaContatoModel);

    PessoaContatoModel editar(PessoaContatoModel pessoaContatoModel);

    void deletar(Long id);

    List<PessoaContatoModel> contatosPorPessoa(Long pessoaId);

    List<PessoaContatoModel> getAll();

    List<PessoaContatoModel> buscar(PessoaContatoModel pessoaContatoFiltro);

    PessoaContatoModel burcarPorId(Long id);
}
