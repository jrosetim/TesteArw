package com.jrosetim.testearw.serviceImpl;

import com.jrosetim.testearw.model.PessoaContatoModel;
import com.jrosetim.testearw.repository.PessoaContatoRepository;
import com.jrosetim.testearw.service.PessoaContatoService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaContatoServiceImpl implements PessoaContatoService {

    @Autowired
    private PessoaContatoRepository repository;

    @Override
    @Transactional
    public PessoaContatoModel salvar(PessoaContatoModel pessoaContatoModel) {
        return repository.save(pessoaContatoModel);
    }

    @Override
    @Transactional
    public PessoaContatoModel editar(PessoaContatoModel pessoaContatoModel) {
        Objects.requireNonNull(pessoaContatoModel.getId());

        return repository.save(pessoaContatoModel);
    }

    @Override
    @Transactional
    public void deletar(Long  id) {
        repository.deleteById(id);
    }

    @Override
    public List<PessoaContatoModel> contatosPorPessoa(Long pessoaId) {
        List<PessoaContatoModel> pessoaContato = repository.findByPessoaId(pessoaId);

        return pessoaContato;
    }

    @Override
    public List<PessoaContatoModel> getAll() {
        return repository.findAll();
    }


    @Override
    public List<PessoaContatoModel> buscar(PessoaContatoModel pessoaContatoFiltro) {
        Example example = Example.of(pessoaContatoFiltro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) );

        return repository.findAll(example);
    }

    @Override
    public PessoaContatoModel burcarPorId(Long id) {
        return repository.findById(id).get();
    }


}
