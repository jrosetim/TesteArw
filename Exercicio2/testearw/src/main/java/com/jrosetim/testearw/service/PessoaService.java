package com.jrosetim.testearw.service;

import com.jrosetim.testearw.model.PessoaModel;
import java.util.List;
import java.util.Optional;

public interface PessoaService {

    PessoaModel salvar(PessoaModel pessoaModel);

    PessoaModel editar(PessoaModel pessoaModel);

    void deletar(PessoaModel pessoaModel);

    Optional<PessoaModel> filtrarPorId(Long id);

    List<PessoaModel> getAll();
}
