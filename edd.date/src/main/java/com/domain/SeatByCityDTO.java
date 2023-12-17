package com.air_force.domain;

import com.air_force.entity.TicketInfo;
import lombok.Data;

import java.util.List;

@Data
public class SeatByCityDTO {
    private String airline;
    private String raceMonth;
    private List<TicketInfo> date;
}
