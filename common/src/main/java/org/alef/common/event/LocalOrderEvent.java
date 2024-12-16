package org.alef.common.event;

import org.springframework.context.ApplicationEvent;

public class LocalOrderEvent extends ApplicationEvent {
    private String orderId;

    public LocalOrderEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}