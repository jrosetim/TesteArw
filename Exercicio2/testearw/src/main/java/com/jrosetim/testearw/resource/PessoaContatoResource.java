package com.jrosetim.testearw.resource;

import com.jrosetim.testearw.DTO.PessoaContatoDTO;
import com.jrosetim.testearw.exception.RegraNegocioException;
import com.jrosetim.testearw.model.PessoaContatoModel;
import com.jrosetim.testearw.model.PessoaModel;
import com.jrosetim.testearw.service.PessoaContatoService;
import com.jrosetim.testearw.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoacontato")
public class PessoaContatoResource {

    @Autowired
    private PessoaContatoService service;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<PessoaContatoModel> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody PessoaContatoDTO dto){
        Optional<PessoaModel> pessoa = pessoaService.filtrarPorId(dto.getPessoaid());

        if (pessoa.isPresent()){
//            PessoaContatoModel pessoaContato = PessoaContatoModel
//                    .builder()
//                    .nome(dto.getNome())
//                    .telefone(dto.getTelefone())
//                    .celular(dto.getCelular())
//                    .pessoa(pessoa.get())
//                    .build();

            PessoaContatoModel pessoaContato = convertDtoToEntity(dto);

            PessoaContatoModel pessoaContatoSalva = service.salvar(pessoaContato);

            return ResponseEntity.ok(pessoaContatoSalva);
        }else{
            throw new RegraNegocioException("Pessoa não encontrada");
        }
    }


    @GetMapping("{idpessoa}")
    public List<PessoaContatoModel> buscaPorPessoaId(@PathVariable Long idpessoa){
        Optional<PessoaModel> pessoa = pessoaService.filtrarPorId(idpessoa);

        if (pessoa.isPresent()){
            List<PessoaContatoModel> contatos = service.contatosPorPessoa(pessoa.get().getId());

            return contatos;
        }else{
            throw new RegraNegocioException("Pessoa não cadastrada.");
        }
    }

    @PutMapping
    public PessoaContatoModel editar(@RequestBody PessoaContatoDTO dto){
        PessoaContatoModel pessoaContato = convertDtoToEntity(dto);

        return service.editar(pessoaContato);
    }

    private PessoaContatoModel convertDtoToEntity(PessoaContatoDTO dto){
        PessoaContatoModel pessoaContato = new PessoaContatoModel();

        if(dto.getId() > 0){
            pessoaContato.setId(dto.getId());
        }

        if (dto.getNome().length() > 0){
            pessoaContato.setNome(dto.getNome());
        }

        if (dto.getTelefone().length() > 0){
            pessoaContato.setTelefone(dto.getTelefone());
        }

        if (dto.getCelular().length() > 0){
            pessoaContato.setCelular(dto.getCelular());
        }

        if (dto.getPessoaid() > 0) {
            Optional<PessoaModel> pessoa = pessoaService.filtrarPorId(dto.getPessoaid());
            pessoaContato.setPessoa(pessoa.get());
        }

        return pessoaContato;
    }
}
