package org.lab1.interfaces;

import org.lab1.models.Buy;

public interface MoneyTransactions {

    public void spendMoney(Buy buy);

    public void makeMoney(int cash);
}
