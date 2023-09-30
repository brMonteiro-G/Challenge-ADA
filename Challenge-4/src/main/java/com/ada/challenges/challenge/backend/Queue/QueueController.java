package com.ada.challenges.challenge.backend.Queue;

import com.ada.challenges.challenge.backend.aws.SqsConfiguration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final SqsConfiguration sqsAsyncClient;

    public QueueController(SqsConfiguration sqsAsyncClient) {
        this.sqsAsyncClient = sqsAsyncClient;
    }


    @GetMapping(path = "/serve-client")
    @ResponseStatus(HttpStatus.OK)
    public String serveClient() {
        return sqsAsyncClient.getPayloadMessage();
    }

    @Description("Not implemented yet for use with sqs")
    @GetMapping(path = "/queue-status")
    @ResponseStatus(HttpStatus.OK)
    public Object peekQueue() {
        return sqsAsyncClient.getQueueStatus();
    }

}
