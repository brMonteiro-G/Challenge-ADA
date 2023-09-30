package com.ada.challenges.challenge.backend.aws;

import com.ada.challenges.challenge.backend.LegalPerson.LegalPerson;
import com.ada.challenges.challenge.backend.NaturalPerson.NaturalPerson;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    private final SnsTemplate snsTemplate;
    private final String topicArn;


    public NotificationService(SnsTemplate snsTemplate, @Value("${aws.topic-arn}") String topicArn) {
        this.snsTemplate = snsTemplate;
        this.topicArn = topicArn;
    }

    public void sendNotification(LegalPerson legalPerson){
        snsTemplate.sendNotification(topicArn, legalPerson, "New message: Legal Person added");
    }

    public void sendNotification(NaturalPerson naturalPerson){
        snsTemplate.sendNotification(topicArn, naturalPerson, "New message: NaturalPerson Person added");

    }



    

}
