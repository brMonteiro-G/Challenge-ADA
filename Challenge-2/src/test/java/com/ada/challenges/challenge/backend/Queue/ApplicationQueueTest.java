package com.ada.challenges.challenge.backend.Queue;

import com.ada.challenges.challenge.backend.Exception.QueueEmptyException;
import com.ada.challenges.challenge.backend.Exception.QueueFullException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationQueueTest {
    private ApplicationQueue applicationQueue;

  @BeforeEach
  void init() {
       this.applicationQueue = new ApplicationQueue(3);

        }


    @Test
    void givenAnObject_WhenItCallsInsertMethod_ThenAddAListOfObjects() {
        Object objectTest = new Object();

        this.applicationQueue.insert(objectTest);
        assertEquals(applicationQueue.getQueueArray()[0],objectTest);
        assertEquals(applicationQueue.getQueueArray()[1],0);

    }

    @Test
    void givenAnObject_WhenItCallsInsertMethod_ThenThrowsAnQueueFullException() {
        Object objectTest = new Object();
        Object objectTest1 = new Object();
        Object objectTest2 = new Object();
        Object objectTest3 = new Object();


        this.applicationQueue.insert(objectTest);
        this.applicationQueue.insert(objectTest1);
        this.applicationQueue.insert(objectTest2);

        QueueFullException exception = assertThrows(QueueFullException.class,
                () -> this.applicationQueue.insert(objectTest3),
        "Sorry, but the queue is full"
        );

        assertEquals(exception.getMessage(),"Sorry, but the queue is full");
    }

    @Test
    void givenAnObject_WhenItCallsRemoveMethod_ThenRemoveAnObjectFromQueue() {
        String objectTest = "teste1";
        String objectTest1 = "teste2";
        String objectTest2 = "teste3";

        this.applicationQueue.insert(objectTest);
        this.applicationQueue.insert(objectTest1);

       this.applicationQueue.insert(objectTest2);
        this.applicationQueue.remove();

        assertEquals(applicationQueue.getQueueArray()[0],"0");

  }


    @Test
    void givenAnObject_WhenItCallsRemoveAMethod_ThenThrowsAnQueueEmptyException() {

        QueueEmptyException exception = assertThrows(QueueEmptyException.class,
                () -> this.applicationQueue.remove(),
                "queue is empty"
        );

        assertEquals(exception.getMessage(),"queue is empty");
    }

    @Test
    void givenAnyParameters_WhenItCallsPeekMethod_ThenReturnAListOfObjects() {

        String objectTest = "teste1";
        String objectTest1 = "teste2";
        String objectTest2 = "teste3";

        this.applicationQueue.insert(objectTest);
        this.applicationQueue.insert(objectTest1);

        this.applicationQueue.insert(objectTest2);
        this.applicationQueue.remove();

        assertEquals(applicationQueue.getQueueArray()[0],"0");

    }

    @Test
    void givenAnyParameters_WhenItCallsFillQueueMethod_ThenReturnAVoidMethod() {

        Arrays.stream(applicationQueue.getQueueArray()).forEach((element->assertEquals(element, "0")));

    }



}