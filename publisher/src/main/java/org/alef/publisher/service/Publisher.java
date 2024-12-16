package org.alef.publisher.service;

import org.alef.common.event.LocalOrderEvent;
import org.alef.common.event.RemoteOrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Publisher {
    public static final Logger LOGGER = Logger.getLogger(Publisher.class.getName());

    private final RabbitTemplate rabbitTemplate;
    private final KafkaTemplate<String, RemoteOrderEvent> kafkaTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Publisher(RabbitTemplate rabbitTemplate, KafkaTemplate<String, RemoteOrderEvent> kafkaTemplate, ApplicationEventPublisher applicationEventPublisher) {
        this.rabbitTemplate = rabbitTemplate;
        this.kafkaTemplate = kafkaTemplate;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishRemoteOrderEvent(RemoteOrderEvent event) {
        rabbitTemplate.convertAndSend("orderQueue",  event);
        kafkaTemplate.send("orderTopic", event);
        LOGGER.info("Published remote order event: " + event.getOrderId());
    }

    public void publishLocalOrderEvent(LocalOrderEvent event) {
        applicationEventPublisher.publishEvent(event);
        LOGGER.info("Published locally order event: " + event.getOrderId());
    }
}