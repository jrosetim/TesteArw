package com.jrosetim.testearw.repository;

import com.jrosetim.testearw.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

}
