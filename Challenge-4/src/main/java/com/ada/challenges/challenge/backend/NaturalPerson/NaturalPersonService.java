package com.ada.challenges.challenge.backend.NaturalPerson;

import com.ada.challenges.challenge.backend.Constants.Constants;
import com.ada.challenges.challenge.backend.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge.backend.Exception.UserNotFoundException;
import com.ada.challenges.challenge.backend.LegalPerson.LegalPerson;
import com.ada.challenges.challenge.backend.Queue.ApplicationQueue;
import com.ada.challenges.challenge.backend.Utils.ZeroFormatter;
import com.ada.challenges.challenge.backend.aws.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository naturalPersonRepository;
    private final ModelMapper modelMapper;
    private final ApplicationQueue applicationQueue;

    private final NotificationService notificationService;



    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository, ModelMapper modelMapper, ApplicationQueue applicationQueue, NotificationService notificationService) {
        this.naturalPersonRepository = naturalPersonRepository;
        this.modelMapper = modelMapper;
        this.applicationQueue = applicationQueue;
        this.notificationService = notificationService;
    }


    public List<NaturalPerson> getAll() {
        return this.naturalPersonRepository.findAll();

    }


    public NaturalPerson create(NaturalPersonDTO naturalPersonDTO) {

        NaturalPerson naturalPerson = modelMapper.map(naturalPersonDTO, NaturalPerson.class);

        String formattedValue = ZeroFormatter.formatter(naturalPerson.getCpf(), Constants.CNPJ_SIZE);

        naturalPerson.setCpf(formattedValue);

        if (naturalPersonRepository.findByCpf(naturalPerson.getCpf()).isEmpty()) {
            NaturalPerson savedLegalPerson = this.naturalPersonRepository.save(naturalPerson);
            notificationService.sendNotification(naturalPerson);
            return savedLegalPerson;
        }

        throw new UserAlreadyRegisteredException("User already exists: " + naturalPerson.getCpf());

    }

    public NaturalPerson update(String cpf, NaturalPersonDTO naturalPersonDTO) {

        NaturalPerson naturalPerson = modelMapper.map(naturalPersonDTO, NaturalPerson.class);


        NaturalPerson oldNaturalPerson = naturalPersonRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cpf: " + cpf));

        this.naturalPersonRepository.delete(oldNaturalPerson);
        NaturalPerson savedNaturalPerson = this.naturalPersonRepository.save(naturalPerson);

        notificationService.sendNotification(naturalPerson);
        return savedNaturalPerson;
    }

    public void delete(String cpf) {

        NaturalPerson naturalPerson = naturalPersonRepository.findByCpf(cpf)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cpf: " + cpf));

        this.naturalPersonRepository.delete(naturalPerson);

    }
}
