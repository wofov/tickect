package com.air_force.domain;

import lombok.Data;

import java.util.List;

@Data
public class AirlineDTO {
    private String airline;
    private List<DateInfoDTO> dateInfo;

}