package org.example.fourdollar.common.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Money {

    private final String currency;
    private final BigDecimal amount;

    public Money(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

}
