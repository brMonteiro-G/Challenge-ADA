package com.ada.challenges.challenge.backend.LegalPerson;

import com.ada.challenges.challenge.backend.Constants.Constants;
import com.ada.challenges.challenge.backend.Exception.UserAlreadyRegisteredException;
import com.ada.challenges.challenge.backend.Exception.UserNotFoundException;
import com.ada.challenges.challenge.backend.Queue.ApplicationQueue;
import com.ada.challenges.challenge.backend.Utils.ZeroFormatter;
import com.ada.challenges.challenge.backend.aws.NotificationService;
import com.ada.challenges.challenge.backend.aws.SqsConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalPersonService {

    private final LegalPersonRepository legalPersonRepository;
    private final ModelMapper modelMapper;
    private final SqsConfiguration sqsQueue;

    private final NotificationService notificationService;


    public LegalPersonService(LegalPersonRepository legalPersonRepository, ModelMapper modelMapper, SqsConfiguration sqsQueue, NotificationService notificationService) {
        this.legalPersonRepository = legalPersonRepository;
        this.modelMapper = modelMapper;
        this.sqsQueue = sqsQueue;
        this.notificationService = notificationService;
    }


    public List<LegalPerson> getAll() {
        return this.legalPersonRepository.findAll();

    }


    public LegalPerson create(LegalPersonDTO legalPersonDTO) {

        LegalPerson legalPerson = modelMapper.map(legalPersonDTO, LegalPerson.class);

        String formattedValue = ZeroFormatter.formatter(legalPerson.getCnpj(), Constants.CNPJ_SIZE);

        legalPerson.setCnpj(formattedValue);

        if (legalPersonRepository.findByCnpj(legalPerson.getCnpj()).isEmpty()) {
            notificationService.sendNotification(legalPerson);

            return this.legalPersonRepository.save(legalPerson);
        }

        throw new UserAlreadyRegisteredException("User already exists: " + legalPerson.getCnpj());

    }

    public LegalPerson update(String cnpj, LegalPersonDTO legalPersonDTO) {

        LegalPerson legalPerson = modelMapper.map(legalPersonDTO, LegalPerson.class);


        LegalPerson oldLegalPerson = legalPersonRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cnpj: " + cnpj));

        this.legalPersonRepository.delete(oldLegalPerson);
        notificationService.sendNotification(legalPerson);


        return this.legalPersonRepository.save(legalPerson);
    }

    public void delete(String cnpj) {

        LegalPerson legalPerson = legalPersonRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new UserNotFoundException("Does not exist register with cnpj: " + cnpj));

        sqsQueue.getPayloadMessage();
        this.legalPersonRepository.delete(legalPerson);

    }


}
