package com.jrosetim.testearw.repository;

import com.jrosetim.testearw.model.PessoaContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaContatoRepository extends JpaRepository<PessoaContatoModel, Long> {

    List<PessoaContatoModel> findByPessoaId(Long pessoaid);
}
