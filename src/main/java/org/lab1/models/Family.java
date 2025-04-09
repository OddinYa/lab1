package org.lab1.models;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Family {

    public Family(Money money){
        this.money = money;
        userList = new ArrayList<>();
    }

    private Money money;
    private List<User> userList;

    public void addInFamily(User user){
        userList.add(user);
    }

    public float getTotalExpenses() {
        float total = 0;
        for (User user : userList) {
            for (Buy buy : user.getBuyList()) {
                total += buy.getCost();
            }
        }
        return total;
    }

    public float getUserExpenses(String userName) {
        for (User user : userList) {
            if (user.getName().equals(userName)) {
                float total = 0;
                for (Buy buy : user.getBuyList()) {
                    total += buy.getCost();
                }
                return total;
            }
        }
        return 0;
    }

}
