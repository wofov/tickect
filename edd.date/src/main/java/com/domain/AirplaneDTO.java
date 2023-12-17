package com.air_force.domain;

import lombok.Data;

@Data
public class AirplaneDTO {

    private String airline;
    private String raceMonth;

    private String raceMonthStartDate;
    private String raceMonthEndDate;
    private String departCity;
    private String arriveCity;

    private String departTime;
    private String arriveTime;
    private long price;



}