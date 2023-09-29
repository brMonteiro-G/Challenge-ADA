package com.ada.challenges.challenge.backend.Queue;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final ApplicationQueue applicationQueue;

    public QueueController(ApplicationQueue applicationQueue) {
        this.applicationQueue = applicationQueue;
    }


    @GetMapping(path = "/serve-client")
    @ResponseStatus(HttpStatus.OK)
    public Object serveClient() {
        return this.applicationQueue.remove();
    }

    @GetMapping(path = "/queue-status")
    @ResponseStatus(HttpStatus.OK)
    public Object peekQueue() {
        return this.applicationQueue.peek();
    }


}
