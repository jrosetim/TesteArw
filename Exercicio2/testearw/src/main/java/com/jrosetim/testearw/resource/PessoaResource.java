package com.jrosetim.testearw.resource;

import com.jrosetim.testearw.DTO.PessoaDTO;
import com.jrosetim.testearw.model.PessoaModel;
import com.jrosetim.testearw.repository.PessoaRepository;
import com.jrosetim.testearw.service.PessoaContatoService;
import com.jrosetim.testearw.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

    @Autowired
    private PessoaService service;

    @Autowired
    private PessoaContatoService pessoaContatoService;

    @GetMapping
    public List<PessoaModel> getPessoas(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody PessoaDTO dto){
        PessoaModel pessoa = PessoaModel
                .builder()
                .nome(dto.getNome())
                .rg(dto.getRg())
                .datanascimento(dto.getDatanascimento())
                .cpf(dto.getCpf())
                .build();

        PessoaModel pessoaSalva = service.salvar(pessoa);

        return ResponseEntity.ok(pessoaSalva);
    }


    @PutMapping ResponseEntity editar(@RequestBody PessoaDTO dto){
        Objects.requireNonNull(dto.getId());

        PessoaModel pessoa = PessoaModel
                .builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .rg(dto.getRg())
                .datanascimento(dto.getDatanascimento())
                .cpf(dto.getCpf())
                .build();

        PessoaModel pessoaSalva = service.editar(pessoa);

        return ResponseEntity.ok(pessoaSalva);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id){
//        pessoaContatoService.deletarPorPessoa(id);

        service.deletar(id);
    }

}
