package org.alef.publisher.service;

import org.alef.common.event.LocalOrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Listener {
    public static final Logger LOGGER = Logger.getLogger(Publisher.class.getName());

    @EventListener
    public void handleLocalOrderEvent(LocalOrderEvent event) {
        LOGGER.info("Received local order event: " + event.getOrderId());
    }
}
