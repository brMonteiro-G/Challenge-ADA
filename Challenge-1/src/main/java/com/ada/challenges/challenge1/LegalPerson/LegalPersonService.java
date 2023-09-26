package com.ada.challenges.challenge1.LegalPerson;

import com.ada.challenges.challenge1.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge1.Exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalPersonService {

    private final LegalPersonRepository legalPersonRepository;
    private final ModelMapper modelMapper;


    public LegalPersonService(LegalPersonRepository legalPersonRepository, ModelMapper modelMapper) {
        this.legalPersonRepository = legalPersonRepository;
        this.modelMapper = modelMapper;

    }


    public List<LegalPerson> getAll() {
    return this.legalPersonRepository.findAll();

    }


    public LegalPerson create(LegalPersonDTO legalPersonDTO) {

        LegalPerson legalPerson = modelMapper.map(legalPersonDTO, LegalPerson.class);

        legalPersonRepository.findByCnpj(legalPerson.getCnpj())
                .orElseThrow(() -> new UserAlreadyRegisteredException("Does not exist register with cnpj: " + legalPerson.getCnpj()));

        return this.legalPersonRepository.save(legalPerson);

    }

    public LegalPerson update(String cnpj,LegalPersonDTO legalPersonDTO) {

        LegalPerson legalPerson = modelMapper.map(legalPersonDTO, LegalPerson.class);

        LegalPerson oldLegalPerson = legalPersonRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cnpj: " + cnpj));

        this.legalPersonRepository.delete(oldLegalPerson);

        return this.legalPersonRepository.save(legalPerson);
    }
}
