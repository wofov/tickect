package com.air_force.mapper;

import com.air_force.domain.AirlineDTO;
import com.air_force.domain.DateInfoDTO;
import com.air_force.domain.SeatByCityDTO;
import com.air_force.entity.TicketInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AirplaneMapper {

    AirplaneMapper INSTANCE = Mappers.getMapper(AirplaneMapper.class);
    AirlineDTO toAirlineDTO(String airline,
                            List<DateInfoDTO> dateInfo);
    SeatByCityDTO toSeatByCityDTO(String airline,
                                  String raceMonth,
                                  List<TicketInfo> date);


}
