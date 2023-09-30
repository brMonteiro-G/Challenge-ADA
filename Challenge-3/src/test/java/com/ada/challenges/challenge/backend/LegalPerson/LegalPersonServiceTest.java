package com.ada.challenges.challenge.backend.LegalPerson;

import com.ada.challenges.challenge.backend.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge.backend.Exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LegalPersonServiceTest {

    @InjectMocks
    private LegalPersonService legalPersonService;

    @Mock
    private LegalPersonRepository repository;

    @Mock
    private ModelMapper modelMapper;


    @Test
    void  givenAnyParameters_WhenItCallRepository_ThenShouldReturnAListOfLegalPersons() {

        LegalPerson legalPerson = LegalPerson.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();

        List<LegalPerson> legalPersonList = new ArrayList<>();
        legalPersonList.add(legalPerson);

        Mockito.when(this.repository.findAll()).thenReturn( legalPersonList);

        List<LegalPerson> legalPersonServiceResponse = this.legalPersonService.getAll();
        Assertions.assertNotNull(legalPersonServiceResponse);
    }

    @Test
    void givenALegalPerson_WhenItCallRepository_ThenShouldReturnTheSavedLegalPerson() {

        LegalPersonDTO legalPersonDTO = LegalPersonDTO.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();

        LegalPerson legalPerson = LegalPerson.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();


        Mockito.when(modelMapper.map(legalPersonDTO, LegalPerson.class)).thenReturn(legalPerson);
        Mockito.when(this.repository.save(legalPerson)).thenReturn(legalPerson);

        LegalPerson savedLegalPerson = this.legalPersonService.create(legalPersonDTO);
        Assertions.assertNotNull(savedLegalPerson);
    }

    @Test
    void givenALegalPerson_WhenItfindByCnpjSearchingDuplicates_ThenShouldThrowsAnException() {

        LegalPersonDTO legalPersonDTO = LegalPersonDTO.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();

        LegalPerson legalPerson = LegalPerson.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();

        Mockito.when(modelMapper.map(legalPersonDTO, LegalPerson.class)).thenReturn(legalPerson);
        Mockito.when(this.repository.findByCnpj(legalPerson.getCnpj())).thenReturn(Optional.of(legalPerson));



        UserAlreadyRegisteredException exception = assertThrows(UserAlreadyRegisteredException.class,
                () -> this.legalPersonService.create(legalPersonDTO),
                "User already exists: " + legalPerson.getCnpj()
                );

        assertEquals(exception.getMessage(), "User already exists: " + legalPerson.getCnpj());

    }

    @Test
    void givenALegalPerson_WhenItupdateARegister_ThenShouldReturnThrowsAnException() {

        LegalPersonDTO legalPersonDTO = LegalPersonDTO.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();

        LegalPerson legalPerson = LegalPerson.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();
        this.repository.save(legalPerson);
        Mockito.when(modelMapper.map(legalPersonDTO, LegalPerson.class)).thenReturn(legalPerson);
        Mockito.when(this.repository.findByCnpj(legalPerson.getCnpj())).thenReturn(Optional.empty());



        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> this.legalPersonService.update(legalPerson.getCnpj(), legalPersonDTO),
                "Does not exist register with cnpj: " + legalPersonDTO.getCnpj()
        );


        assertEquals(exception.getMessage(), "Does not exist register with cnpj: " + legalPersonDTO.getCnpj());
    }


    @Test
    void givenALegalPerson_WhenItfindByCnpjSearchingARegister_ThenShouldReturnTheUpdatedLegalPerson() {

        LegalPersonDTO legalPersonDTO = LegalPersonDTO.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();

        LegalPerson legalPerson = LegalPerson.builder()
                .cnpj("47625166000167")
                .mcc(451)
                .socialReason("Companhia de Bebidas das Américas")
                .nameEstablishmentContact("Gabriel Monteiro Rocha")
                .cpfEstablishmentContact("50378152834")
                .emailEstablishmentContact("brgabriel.monteiro@gmail.com")
                .build();

        Mockito.when(modelMapper.map(legalPersonDTO, LegalPerson.class)).thenReturn(legalPerson);
        Mockito.when(this.repository.findByCnpj(legalPerson.getCnpj())).thenReturn(Optional.of(legalPerson));
        Mockito.doNothing().when(this.repository).delete(legalPerson);
        Mockito.when(this.repository.save(legalPerson)).thenReturn(legalPerson);

        LegalPerson savedLegalPerson = this.legalPersonService.update("47625166000167" , legalPersonDTO);

        Assertions.assertNotNull(savedLegalPerson);

    }

    @Test
    void delete() {


    }
}