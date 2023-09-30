package com.ada.challenges.challenge.backend.Queue;


import com.ada.challenges.challenge.backend.Exception.QueueEmptyException;
import com.ada.challenges.challenge.backend.Exception.QueueFullException;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class ApplicationQueue {

    private Object[] queueArray;

    private int queueSize;

    private int front, rear, numberOfItems = 0;


    public ApplicationQueue(int size) {

        this.queueSize = size;

        this.queueArray = new Object[size];


        fillArray(queueArray, "0");


    }


    public void insert(Object input) {
        if (numberOfItems + 1 <= queueSize) {
            queueArray[rear] = input;
            rear++;
            numberOfItems++;

            log.info("INSERT" + input + "was added to the queue\n");
            return;
        }

        log.info("Sorry, but the queue is full");
        throw new QueueFullException("Sorry, but the queue is full");

    }

    public Object remove() {
        if (numberOfItems > 0) {
            log.info("REMOVE" + queueArray[front] + "was removed from queue");
            var elementToBeRemoved = queueArray[front];
            queueArray[front] = "0";
            front++;

            numberOfItems--;
            return elementToBeRemoved;
        }

        log.info("sorry but the queue is empty");
        throw new QueueEmptyException("queue is empty");
    }

    public Object[] peek() {
        log.info("the first element is " + queueArray[front]);
        return queueArray;
    }

    private static void fillArray(Object[] a, Object val) {
        for (int i = 0, len = a.length; i < len; i++) {
            a[i] = val;
        }

    }

}
