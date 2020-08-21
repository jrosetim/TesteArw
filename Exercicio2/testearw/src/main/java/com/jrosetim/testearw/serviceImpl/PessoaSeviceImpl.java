package com.jrosetim.testearw.serviceImpl;

import com.jrosetim.testearw.model.PessoaModel;
import com.jrosetim.testearw.repository.PessoaContatoRepository;
import com.jrosetim.testearw.repository.PessoaRepository;
import com.jrosetim.testearw.service.PessoaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaSeviceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaContatoRepository pessoaContatoRepository;

    @Override
    @Transactional
    public PessoaModel salvar(PessoaModel pessoaModel) {
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
}
