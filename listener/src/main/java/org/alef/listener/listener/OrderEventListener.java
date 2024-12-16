package org.alef.listener.listener;

import org.alef.common.event.RemoteOrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OrderEventListener {

    public static final Logger LOGGER = Logger.getLogger(OrderEventListener.class.getName());

    @RabbitListener(queues = "orderQueue")
    public void handleOrderEvent(RemoteOrderEvent event) {
        LOGGER.info("Received RabbitMQ order event - Order ID: " + event.getOrderId());
    }

    @KafkaListener(id = "myId", topics = "orderTopic")
    public void handleKafkaOrderEvent(RemoteOrderEvent event) {
        LOGGER.info("Received Kafka order event - Order ID: " + event.getOrderId());
    }
}