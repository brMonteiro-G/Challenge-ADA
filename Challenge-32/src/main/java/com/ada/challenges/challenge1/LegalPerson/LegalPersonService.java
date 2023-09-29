package com.ada.challenges.challenge1.LegalPerson;

import com.ada.challenges.challenge1.Constants.Constants;
import com.ada.challenges.challenge1.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge1.Exception.UserNotFoundException;
import com.ada.challenges.challenge1.Queue.ApplicationQueue;
import com.ada.challenges.challenge1.Utils.ZeroFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalPersonService {

    private final LegalPersonRepository legalPersonRepository;
    private final ModelMapper modelMapper;
    private final ApplicationQueue applicationQueue;


    public LegalPersonService(LegalPersonRepository legalPersonRepository, ModelMapper modelMapper, ApplicationQueue applicationQueue) {
        this.legalPersonRepository = legalPersonRepository;
        this.modelMapper = modelMapper;
        this.applicationQueue = applicationQueue;
    }


    public List<LegalPerson> getAll() {
        return this.legalPersonRepository.findAll();

    }


    public LegalPerson create(LegalPersonDTO legalPersonDTO) {

        LegalPerson legalPerson = modelMapper.map(legalPersonDTO, LegalPerson.class);

        String formattedValue = ZeroFormatter.formatter(legalPerson.getCnpj(), Constants.CNPJ_SIZE);

        legalPerson.setCnpj(formattedValue);

        if (legalPersonRepository.findByCnpj(legalPerson.getCnpj()).isEmpty()) {
            applicationQueue.insert(legalPerson);
            System.out.println(applicationQueue);
            return this.legalPersonRepository.save(legalPerson);
        }

        throw new UserAlreadyRegisteredException("User already exists: " + legalPerson.getCnpj());

    }

    public LegalPerson update(String cnpj, LegalPersonDTO legalPersonDTO) {

        LegalPerson legalPerson = modelMapper.map(legalPersonDTO, LegalPerson.class);


        LegalPerson oldLegalPerson = legalPersonRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cnpj: " + cnpj));

        this.legalPersonRepository.delete(oldLegalPerson);
        applicationQueue.insert(legalPerson);


        return this.legalPersonRepository.save(legalPerson);
    }

    public void delete(String cnpj) {

        LegalPerson legalPerson = legalPersonRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cnpj: " + cnpj));
        applicationQueue.remove();
        System.out.println(applicationQueue);
        this.legalPersonRepository.delete(legalPerson);

    }


}
