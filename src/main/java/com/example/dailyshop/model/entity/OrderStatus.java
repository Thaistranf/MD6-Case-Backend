package com.example.dailyshop.model.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
    Paid("Paid"),
    Unpaid("Unpaid"),
    Cancel("Cancel");
    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

}

