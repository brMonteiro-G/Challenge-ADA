package com.ada.challenges.challenge.backend.Queue;

import com.ada.challenges.challenge.backend.LegalPerson.LegalPerson;
import com.ada.challenges.challenge.backend.aws.SqsConfiguration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final SqsConfiguration sqsAsyncClient;
    private final ApplicationQueue applicationQueue;

    public QueueController(SqsConfiguration sqsAsyncClient, ApplicationQueue applicationQueue) {
        this.sqsAsyncClient = sqsAsyncClient;
        this.applicationQueue = applicationQueue;
    }


    @GetMapping(path = "/serve-client")
    @ResponseStatus(HttpStatus.OK)
    public Object serveClient() {
        Object response = sqsAsyncClient.getPayloadMessage();
        applicationQueue.insert(response);
        return response;
    }

    @Description("Not implemented yet for use with sqs")
    @GetMapping(path = "/history-queue")
    @ResponseStatus(HttpStatus.OK)
    public Object peekQueue() {
        return applicationQueue.peek();
    }

}
