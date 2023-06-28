package com.example.dev.entity;

public enum OrderStatus {

    ORDER("주문"),
    PAYMENT("결제"),
    PROGRESS("진행"),
    CANCEL("취소"),
    REFUND("환불"),
    COMPLETE("완료")
    ;

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
