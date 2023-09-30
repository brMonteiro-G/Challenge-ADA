package com.ada.challenges.challenge.backend.aws;

import com.ada.challenges.challenge.backend.LegalPerson.LegalPerson;
import io.awspring.cloud.sqs.listener.SqsMessageListenerContainer;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.Arrays;

@Log4j2
@Getter
@Configuration
public class SqsConfiguration {

    @Value("${aws.queue-name}")
    private String queueName;
    private Object payloadMessage;
    private String queueStatus;


    @Bean
    public SqsTemplate sqsTemplateConfig(SqsAsyncClient sqsAsyncClient){
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build();
    }

    @Bean
   public SqsMessageListenerContainer<LegalPerson> receiveMessage(SqsAsyncClient sqsAsyncClient){
        return new SqsMessageListenerContainer.Builder<LegalPerson>()
                .sqsAsyncClient(sqsAsyncClient).queueNames(this.queueName)
                .messageListener((message -> this.payloadMessage = message.getPayload())).build();
    }

    @Bean
    public SqsMessageListenerContainer.Builder<LegalPerson[]> receiveManyMessages(SqsAsyncClient sqsAsyncClient){
        return new SqsMessageListenerContainer.Builder<LegalPerson[]>()
                .sqsAsyncClient(sqsAsyncClient).queueNames(this.queueName).messageListener(message -> this.queueStatus = Arrays.toString(message.getPayload()));
    }




}
