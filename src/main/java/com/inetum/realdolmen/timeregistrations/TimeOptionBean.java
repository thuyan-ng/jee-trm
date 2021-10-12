package com.inetum.realdolmen.timeregistrations;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Model;

@Model
public class TimeOptionBean {

    private List<Integer> timeOptions = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

    public List<Integer> getTimeOptions() {
        return this.timeOptions;
    }

}
