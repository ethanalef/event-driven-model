package org.alef.publisher.controller;

import org.alef.common.event.LocalOrderEvent;
import org.alef.common.event.RemoteOrderEvent;
import org.alef.publisher.service.Publisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final Publisher publisher;

    public OrderController(Publisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/remoteOrder")
    public String remoteOrder(@RequestParam String orderId) {
        publisher.publishRemoteOrderEvent(new RemoteOrderEvent(orderId));
        return "Order created remotely: " + orderId;
    }

    @PostMapping("/localOrder")
    public String localOrder(@RequestParam String orderId) {
        publisher.publishLocalOrderEvent(new LocalOrderEvent(this, orderId));
        return "Order created locally: " + orderId;
    }
}