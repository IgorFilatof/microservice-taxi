package com.example.order.model;

public enum OrderStatus {
    NEW(1),
    CREATED(2),
    ACCEPTED_BY_DRIVER(3),
    IN_PROGRESS(4),
    CLOSED(5);

    private int value;

    public int getValue() {
        return value;
    }

    OrderStatus(int value) {
        this.value = value;
    }

    public static OrderStatus parse(int val) {
        OrderStatus orderStatus = null;
        for (OrderStatus items : OrderStatus.values()) {
            if (items.getValue() == val) {
                orderStatus = items;
                break;
            }
        }
        return orderStatus;
    }
}