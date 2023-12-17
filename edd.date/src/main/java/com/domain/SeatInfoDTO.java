package com.air_force.domain;

import com.air_force.entity.SeatInfo;
import lombok.Data;

import java.util.List;
@Data
public class SeatInfoDTO {
    private long price;
    private long seatNumber;
    private List<SeatInfo> seatInfo;
    private long spareSeat;
}
