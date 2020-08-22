package com.jrosetim.testearw.serviceImpl;

import com.jrosetim.testearw.exception.RegraNegocioException;
import com.jrosetim.testearw.model.PessoaModel;
import com.jrosetim.testearw.repository.PessoaContatoRepository;
import com.jrosetim.testearw.repository.PessoaRepository;
import com.jrosetim.testearw.service.PessoaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class PessoaSeviceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaContatoRepository pessoaContatoRepository;

    @Override
    @Transactional
    public PessoaModel salvar(PessoaModel pessoaModel) {
        validaDados(pessoaModel);


        return repository.save(pessoaModel);
    }

    @Override
    @Transactional
    public PessoaModel editar(PessoaModel pessoaModel) {
        Objects.requireNonNull(pessoaModel.getId());

        return repository.save(pessoaModel);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PessoaModel> filtrarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PessoaModel> getAll() {
        return repository.findAll();
    }

    @Override
    public List<PessoaModel> buscar(PessoaModel pessoaFiltro) {
        Example example = Example.of(pessoaFiltro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) );

        return repository.findAll(example);
    }

    public void validaDados(PessoaModel pessoaModel){
        if(pessoaModel.getNome().length() < 0 || pessoaModel.getNome().trim() != " "){
            throw new RegraNegocioException("Digite um nome para a pessoa");
        }
    }
}
