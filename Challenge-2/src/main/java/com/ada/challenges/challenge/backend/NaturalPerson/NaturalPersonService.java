package com.ada.challenges.challenge.backend.NaturalPerson;

import com.ada.challenges.challenge.backend.Constants.Constants;
import com.ada.challenges.challenge.backend.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge.backend.Exception.UserNotFoundException;
import com.ada.challenges.challenge.backend.Queue.ApplicationQueue;
import com.ada.challenges.challenge.backend.Utils.ZeroFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository naturalPersonRepository;
    private final ModelMapper modelMapper;
    private final ApplicationQueue applicationQueue;


    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository, ModelMapper modelMapper, ApplicationQueue applicationQueue) {
        this.naturalPersonRepository = naturalPersonRepository;
        this.modelMapper = modelMapper;
        this.applicationQueue = applicationQueue;
    }


    public List<NaturalPerson> getAll() {
        return this.naturalPersonRepository.findAll();

    }


    public NaturalPerson create(NaturalPersonDTO naturalPersonDTO) {

        NaturalPerson legalPerson = modelMapper.map(naturalPersonDTO, NaturalPerson.class);

        String formattedValue = ZeroFormatter.formatter(legalPerson.getCpf(), Constants.CNPJ_SIZE);

        legalPerson.setCpf(formattedValue);

        if (naturalPersonRepository.findByCpf(legalPerson.getCpf()).isEmpty()) {
            applicationQueue.insert(legalPerson);
            return this.naturalPersonRepository.save(legalPerson);
        }

        throw new UserAlreadyRegisteredException("User already exists: " + legalPerson.getCpf());

    }

    public NaturalPerson update(String cpf, NaturalPersonDTO naturalPersonDTO) {

        NaturalPerson naturalPerson = modelMapper.map(naturalPersonDTO, NaturalPerson.class);


        NaturalPerson oldNaturalPerson = naturalPersonRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cpf: " + cpf));

        this.naturalPersonRepository.delete(oldNaturalPerson);

        return this.naturalPersonRepository.save(naturalPerson);
    }

    public void delete(String cpf) {

        NaturalPerson naturalPerson = naturalPersonRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cpf: " + cpf));

        this.naturalPersonRepository.delete(naturalPerson);

    }
}
