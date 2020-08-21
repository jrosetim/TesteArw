package com.jrosetim.testearw.repository;

import com.jrosetim.testearw.model.PessoaContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaContatoRepository extends JpaRepository<PessoaContatoModel, Long> {

    List<PessoaContatoModel> findByPessoaId(Long pessoaid);
}
