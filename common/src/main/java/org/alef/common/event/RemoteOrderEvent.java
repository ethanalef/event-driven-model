package org.alef.common.event;

import java.io.Serializable;

public class RemoteOrderEvent implements Serializable {
    private String orderId;

    public RemoteOrderEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}