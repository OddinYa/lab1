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
}
