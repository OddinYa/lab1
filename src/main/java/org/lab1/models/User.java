package org.lab1.models;

import lombok.Getter;
import lombok.Setter;
import org.lab1.interfaces.MakeMoney;
import org.lab1.interfaces.SpendMoney;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User implements MakeMoney, SpendMoney {

    public User(Money money,String name){

        this.money = money;
        this.spending = 0;
        this.name = name;
        this.buyList = new ArrayList<>();

    }
    private Money money;
    private int spending;
    private String name;

    private List<Buy> buyList;


    @Override
    public void spendMoney(Buy buy) {
        buyList.add(buy);
        spending+=buy.getCost();
        money.setCash(money.getCash()- buy.getCost());
    }

    @Override
    public void makeMoney(float cash) {
        money.setCash(money.getCash()+cash);
    }
}
