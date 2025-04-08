package org.lab1.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Money {

    public Money(float cash){

        this.cash = cash;
    }
    private float cash;
}
