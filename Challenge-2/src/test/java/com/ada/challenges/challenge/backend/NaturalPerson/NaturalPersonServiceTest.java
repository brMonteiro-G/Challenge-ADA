package com.ada.challenges.challenge.backend.NaturalPerson;

import com.ada.challenges.challenge.backend.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge.backend.Exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NaturalPersonServiceTest {

    @InjectMocks
    private NaturalPersonService service;

    @Mock
    private NaturalPersonRepository repository;

    @Mock
    private ModelMapper modelMapper;


    @Test
    void  givenAnyParameters_WhenItCallRepository_ThenShouldReturnAListOfLegalPersons() {

        NaturalPerson naturalPerson = NaturalPerson.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();

        List<NaturalPerson> naturalPersonList = new ArrayList<>();
        naturalPersonList.add(naturalPerson);

        Mockito.when(this.repository.findAll()).thenReturn( naturalPersonList);

        List<NaturalPerson> naturalPersonServiceResponse = this.service.getAll();
        Assertions.assertNotNull(naturalPersonServiceResponse);
    }

    @Test
    void givenALegalPerson_WhenItCallRepository_ThenShouldReturnTheSavedLegalPerson() {

        NaturalPersonDTO naturalPersonDTO = NaturalPersonDTO.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();

        NaturalPerson naturalPerson = NaturalPerson.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();

        Mockito.when(modelMapper.map(naturalPersonDTO, NaturalPerson.class)).thenReturn(naturalPerson);
        Mockito.when(this.repository.save(naturalPerson)).thenReturn(naturalPerson);

        NaturalPerson naturalLegalPerson = this.service.create(naturalPersonDTO);
        Assertions.assertNotNull(naturalLegalPerson);
    }

    @Test
    void givenANaturalPerson_WhenItfindByCpfSearchingDuplicates_ThenShouldThrowsAnException() {

        NaturalPersonDTO naturalPersonDTO = NaturalPersonDTO.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();

        NaturalPerson naturalPerson = NaturalPerson.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();
        Mockito.when(modelMapper.map(naturalPersonDTO, NaturalPerson.class)).thenReturn(naturalPerson);
        Mockito.when(this.repository.findByCpf(naturalPerson.getCpf())).thenReturn(Optional.of(naturalPerson));



        UserAlreadyRegisteredException exception = assertThrows(UserAlreadyRegisteredException.class,
                () -> this.service.create(naturalPersonDTO),
                "User already exists: " + naturalPerson.getCpf()
        );

        assertEquals(exception.getMessage(), "User already exists: " + naturalPerson.getCpf());

    }

    @Test
    void givenALegalPerson_WhenItupdateARegister_ThenShouldReturnThrowsAnException() {

        NaturalPersonDTO naturalPersonDTO = NaturalPersonDTO.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();

        NaturalPerson naturalPerson = NaturalPerson.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();
        this.repository.save(naturalPerson);
        Mockito.when(modelMapper.map(naturalPersonDTO, NaturalPerson.class)).thenReturn(naturalPerson);
        Mockito.when(this.repository.findByCpf(naturalPerson.getCpf())).thenReturn(Optional.empty());



        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> this.service.update(naturalPerson.getCpf(), naturalPersonDTO),
                "Does not exist register with cpf: " + naturalPersonDTO.getCpf()
        );


        assertEquals(exception.getMessage(), "Does not exist register with cpf: " + naturalPersonDTO.getCpf());
    }


    @Test
    void givenALegalPerson_WhenItfindByCnpjSearchingARegister_ThenShouldReturnTheUpdatedNaturalPerson() {

        NaturalPersonDTO naturalPersonDTO = NaturalPersonDTO.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();


        NaturalPerson naturalPerson = NaturalPerson.builder()
                .cpf("50378152834")
                .mcc(451)
                .personEmail("brgabriel.monteiro@gmail.com")
                .personName("Teste")
                .build();

        Mockito.when(modelMapper.map(naturalPersonDTO, NaturalPerson.class)).thenReturn(naturalPerson);
        Mockito.when(this.repository.findByCpf(naturalPerson.getCpf())).thenReturn(Optional.of(naturalPerson));
        Mockito.doNothing().when(this.repository).delete(naturalPerson);
        Mockito.when(this.repository.save(naturalPerson)).thenReturn(naturalPerson);

        NaturalPerson savedNaturalPerson = this.service.update("50378152834" , naturalPersonDTO);

        Assertions.assertNotNull(savedNaturalPerson);

    }

}