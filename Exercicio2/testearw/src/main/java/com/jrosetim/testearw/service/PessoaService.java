package com.jrosetim.testearw.service;

import com.jrosetim.testearw.model.PessoaModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    PessoaModel salvar(PessoaModel pessoaModel);

    PessoaModel editar(PessoaModel pessoaModel);

    void deletar(Long id);

    Optional<PessoaModel> filtrarPorId(Long id);

    List<PessoaModel> getAll();

    List<PessoaModel> buscar(PessoaModel pessoaFiltro);
}
