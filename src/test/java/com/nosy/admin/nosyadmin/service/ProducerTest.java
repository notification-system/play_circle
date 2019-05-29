package com.nosy.admin.nosyadmin.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;


@RunWith(MockitoJUnitRunner.class)

public class ProducerTest {
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;


    @InjectMocks
    private Producer producer;

    @Before
    public void setUp(){

    }


    @Test(expected = Test.None.class)
    public void sendMessage() {
        String topic="topic";
        String message="Test Message";
        producer.sendMessage(message);

    }
}
