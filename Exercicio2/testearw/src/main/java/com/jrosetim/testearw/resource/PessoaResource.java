package com.jrosetim.testearw.resource;

import com.jrosetim.testearw.DTO.PessoaDTO;
import com.jrosetim.testearw.exception.RegraNegocioException;
import com.jrosetim.testearw.model.PessoaContatoModel;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<PessoaModel> getPessoas(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "rg", required = false) String rg,
            @RequestParam(value = "cpf", required = false) String cpf
    ){
        PessoaModel pessoaFiltro = new PessoaModel();

        pessoaFiltro.setNome(nome);
        pessoaFiltro.setRg(rg);
        pessoaFiltro.setCpf(cpf);

        List<PessoaModel> pessoas = service.buscar(pessoaFiltro);

        return pessoas;
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

        try{
            PessoaModel pessoaSalva = service.salvar(pessoa);

            return ResponseEntity.ok(pessoaSalva);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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
        List<PessoaContatoModel> contatos = pessoaContatoService.contatosPorPessoa(id);

        for (int i = 0; i < contatos.size(); i++){
            pessoaContatoService.deletar(contatos.get(i));
        }

        service.deletar(id);
    }

}
