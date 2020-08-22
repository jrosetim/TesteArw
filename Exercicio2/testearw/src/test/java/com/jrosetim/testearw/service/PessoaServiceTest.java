package com.jrosetim.testearw.service;


import com.jrosetim.testearw.exception.RegraNegocioException;
import com.jrosetim.testearw.model.PessoaModel;
import com.jrosetim.testearw.repository.PessoaRepository;
import com.jrosetim.testearw.serviceImpl.PessoaSeviceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PessoaServiceTest {

    @MockBean
    private PessoaRepository pessoaRepository;

    @SpyBean
    private PessoaSeviceImpl pessoaSevice;

    public static PessoaModel pessoaTest(){
        return PessoaModel
                .builder()
                .nome("Julio")
                .rg("123456789")
                .datanascimento( LocalDate.now() )
                .cpf("05570483988")
                .build();
    }

    @Test
    public void pessoaCadastradaComSucesso(){

        PessoaModel pessoaModel = pessoaTest();
        pessoaModel.setId(1l);

        Mockito.doNothing().when(pessoaSevice).validaDados(pessoaModel);

        Mockito.when(pessoaRepository.save(Mockito.any(PessoaModel.class))).thenReturn(pessoaModel);

        PessoaModel pessoaSalva = pessoaSevice.salvar(pessoaModel);

        Assertions.assertNotNull(pessoaModel);
        Assertions.assertEquals("Julio", pessoaSalva.getNome());
        Assertions.assertEquals("123456789", pessoaSalva.getRg());
        Assertions.assertEquals("05570483988", pessoaSalva.getCpf());
    }

    @Test
    public void pessoaCadastradaSemNome(){
        RegraNegocioException exception = Assertions.assertThrows( RegraNegocioException.class, () -> {
            PessoaModel pessoaModel = pessoaTest();
            pessoaModel.setId(1l);
            pessoaModel.setNome("");

            Mockito.when(pessoaRepository.save(Mockito.any(PessoaModel.class))).thenReturn(pessoaModel);

            PessoaModel pessoaSalva = pessoaSevice.salvar(pessoaModel);
        } );

        Assertions.assertTrue(exception.getMessage().contains("Digite um nome para a pessoa"));
    }

}
