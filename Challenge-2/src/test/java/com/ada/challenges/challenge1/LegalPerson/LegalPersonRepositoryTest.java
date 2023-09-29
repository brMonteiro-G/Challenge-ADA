package com.ada.challenges.challenge1.LegalPerson;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
class LegalPersonRepositoryTest {

    @Autowired
    private LegalPersonRepository repository;

    @Test
    public void creatLegalPerson(){

        LegalPerson legalPerson = LegalPerson.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();


        repository.save(legalPerson);

        Assertions.assertThat(legalPerson.getId()).isGreaterThan(0);



    }
    @Test
    public void readLegalPerson(){

        LegalPerson legalPerson = repository.findByCnpj("47625166000167").get();

        Assertions.assertThat(legalPerson.getCnpj()).isEqualTo("47625166000167");

    }

    @Test
    public void readAllLegalPerson(){

        List<LegalPerson> legalPersonList  = repository.findAll();

        Assertions.assertThat(legalPersonList.size()).isGreaterThan(0);

    }

    @Test
    public void updateLegalPerson(){

        LegalPerson legalPerson = repository.findByCnpj("47625166000167").get();

        legalPerson.setMcc(455);
        legalPerson.setCnpj("05627427000138");
        legalPerson.setSocialReason("Teste");
        legalPerson.setCpfEstablishmentContact("61376417081");
        legalPerson.setEmailEstablishmentContact("gabriel.monteiro@gmail.com");
        legalPerson.setNameEstablishmentContact("Teste");

        LegalPerson legalPersonUpdated =  repository.save(legalPerson);

        Assertions.assertThat(legalPersonUpdated.getMcc()).isEqualTo(455);
        Assertions.assertThat(legalPersonUpdated.getCnpj()).isEqualTo("05627427000138");
        Assertions.assertThat(legalPersonUpdated.getSocialReason()).isEqualTo("Teste");
        Assertions.assertThat(legalPersonUpdated.getCpfEstablishmentContact()).isEqualTo("61376417081");
        Assertions.assertThat(legalPersonUpdated.getNameEstablishmentContact()).isEqualTo("Teste");
        Assertions.assertThat(legalPersonUpdated.getEmailEstablishmentContact()).isEqualTo("gabriel.monteiro@gmail.com");


    }

    @Test
    public void deleteLegalPerson(){

        LegalPerson legalPerson = repository.findByCnpj("47625166000167").get();

        repository.delete(legalPerson);

        LegalPerson anotherLegalPerson = null;

        Optional<LegalPerson> optionalLegalPerson = repository.findByCnpj("47625166000167");

        if(optionalLegalPerson.isPresent()){
            anotherLegalPerson = optionalLegalPerson.get();
        }

        Assertions.assertThat(anotherLegalPerson).isNull();


    }


}