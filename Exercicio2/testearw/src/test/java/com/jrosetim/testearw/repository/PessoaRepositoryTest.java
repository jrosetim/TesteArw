package com.jrosetim.testearw.repository;

import com.jrosetim.testearw.exception.RegraNegocioException;
import com.jrosetim.testearw.model.PessoaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

//    @Autowired
//    TestEntityManager testEntityManager;

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
        PessoaModel pessoa = pessoaTest();

        PessoaModel pessoaSalva = pessoaRepository.save(pessoa);

        Assertions.assertNotNull(pessoaSalva.getId());
    }

}
