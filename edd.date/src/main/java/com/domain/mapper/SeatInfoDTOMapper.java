package com.air_force.domain.mapper;

import com.air_force.domain.SeatInfoDTO;
import com.air_force.entity.SeatInfo;

import java.util.List;

public class SeatInfoDTOMapper {

    public static SeatInfoDTO DTOMapper(long spareSeat,
                                        long price,
                                        long seatNumber,
                                        List<SeatInfo> seatInfo){
        SeatInfoDTO dto = new SeatInfoDTO();
        dto.setSpareSeat(spareSeat);
        dto.setPrice(price);
        dto.setSeatNumber(seatNumber);
        dto.setSeatInfo(seatInfo);
        return dto;
    }

}
