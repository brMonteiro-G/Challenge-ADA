package com.ada.challenges.challenge1.NaturalPerson;

import com.ada.challenges.challenge1.Constants.Constants;
import com.ada.challenges.challenge1.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge1.Exception.UserNotFoundException;
import com.ada.challenges.challenge1.Utils.ZeroFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository naturalPersonRepository;
    private final ModelMapper modelMapper;


    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository, ModelMapper modelMapper) {
        this.naturalPersonRepository = naturalPersonRepository;
        this.modelMapper = modelMapper;

    }


    public List<NaturalPerson> getAll() {
        return this.naturalPersonRepository.findAll();

    }


    public NaturalPerson create(NaturalPersonDTO naturalPersonDTO) {

        NaturalPerson naturalPerson = modelMapper.map(naturalPersonDTO, NaturalPerson.class);

        String formattedValue = ZeroFormatter.formatter(naturalPerson.getCpf() ,Constants.CPF_SIZE );

        naturalPerson.setCpf(formattedValue);

        if(naturalPersonRepository.findByCpf(naturalPerson.getCpf()).isEmpty()){
            return this.naturalPersonRepository.save(naturalPerson);
        }

        throw new UserAlreadyRegisteredException("User already exists: " + naturalPerson.getCpf());

    }

    public NaturalPerson update(String cpf,NaturalPersonDTO naturalPersonDTO) {

        NaturalPerson naturalPerson = modelMapper.map(naturalPersonDTO, NaturalPerson.class);


        NaturalPerson oldNaturalPerson = naturalPersonRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cpf: " + cpf));

        this.naturalPersonRepository.delete(oldNaturalPerson);

        return this.naturalPersonRepository.save(naturalPerson);
    }

    public void delete(String cpf) {

        NaturalPerson naturalPerson =  naturalPersonRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cpf: " + cpf));

        this.naturalPersonRepository.delete(naturalPerson);

    }
}
