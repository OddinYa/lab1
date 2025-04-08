package org.lab1.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Buy {

    private float cost;
    private String name;
    private LocalDate date;

}
