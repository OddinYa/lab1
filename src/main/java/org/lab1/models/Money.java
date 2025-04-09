package org.lab1.models;

import lombok.Getter;
import lombok.Setter;
import org.lab1.interfaces.MakeMoney;

@Getter
@Setter
public class Money implements MakeMoney {

    public Money(float cash){

        this.cash = cash;
    }
    private float cash;

    @Override
    public void makeMoney(float cash) {
        this.cash += cash;
    }
}
