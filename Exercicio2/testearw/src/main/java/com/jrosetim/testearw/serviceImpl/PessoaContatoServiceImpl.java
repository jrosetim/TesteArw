package com.jrosetim.testearw.serviceImpl;

import com.jrosetim.testearw.model.PessoaContatoModel;
import com.jrosetim.testearw.repository.PessoaContatoRepository;
import com.jrosetim.testearw.service.PessoaContatoService;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    public void deletar(PessoaContatoModel pessoaContatoModel) {
        Objects.requireNonNull(pessoaContatoModel.getId());

        repository.deleteById(pessoaContatoModel.getId());
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
    public Optional<PessoaContatoModel> buscarPorId(Long id) {
        return repository.findById(id);
    }

//    @Override
//    public void deletarPorPessoa(Long pessoaId) {
//        repository.deleteByPessoaId(pessoaId);
//    }
}
