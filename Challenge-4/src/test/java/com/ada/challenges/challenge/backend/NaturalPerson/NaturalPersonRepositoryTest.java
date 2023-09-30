package com.ada.challenges.challenge.backend.NaturalPerson;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class NaturalPersonRepositoryTest {

    @Autowired
    private NaturalPersonRepository repository;

    @Test
    public void creatNaturalPerson() {

        NaturalPerson naturalPerson = NaturalPerson.builder().personEmail("gabriel.monteiro@gmail.com")
                .cpf("5037815284")
                .mcc(521)
                .personName("Gabriel Monteiro").build();


        repository.save(naturalPerson);

        Assertions.assertThat(naturalPerson.getId()).isGreaterThan(0);


    }

    @Test
    public void readNaturalPerson() {

        NaturalPerson naturalPerson = repository.findByCpf("50378152834").get();

        Assertions.assertThat(naturalPerson.getCpf()).isEqualTo("50378152834");

    }

    @Test
    public void readAllNaturalPerson() {

        List<NaturalPerson> naturalPersonList = repository.findAll();

        Assertions.assertThat(naturalPersonList.size()).isGreaterThan(0);

    }

    @Test
    public void updateNaturalPerson() {

        NaturalPerson naturalPerson = repository.findByCpf("50378152834").get();

        naturalPerson.setMcc(455);
        naturalPerson.setCpf("5037815289");
        naturalPerson.setPersonEmail("gabriel.monteiro@gmail.com");
        naturalPerson.setPersonName("Teste");

        NaturalPerson naturalPersonUpdated = repository.save(naturalPerson);

        Assertions.assertThat(naturalPersonUpdated.getMcc()).isEqualTo(455);
        Assertions.assertThat(naturalPersonUpdated.getCpf()).isEqualTo("5037815289");
        Assertions.assertThat(naturalPersonUpdated.getPersonName()).isEqualTo("Teste");
        Assertions.assertThat(naturalPersonUpdated.getPersonEmail()).isEqualTo("gabriel.monteiro@gmail.com");
    }

    @Test
    public void deleteNaturalPerson() {

        NaturalPerson naturalPerson  = repository.findByCpf("50378152834").get();

        repository.delete(naturalPerson);

        NaturalPerson anotherNaturalPerson = null;

        Optional<NaturalPerson> optionalNaturalPerson = repository.findByCpf("47625166000164");

        if (optionalNaturalPerson.isPresent()) {
            anotherNaturalPerson = optionalNaturalPerson.get();
        }

        Assertions.assertThat(anotherNaturalPerson).isNull();


    }


}