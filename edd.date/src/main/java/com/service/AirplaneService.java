package com.air_force.service;

import com.air_force.constants.WebConstants;
import com.air_force.domain.*;
import com.air_force.domain.mapper.SeatInfoDTOMapper;
import com.air_force.entity.Airplane;
import com.air_force.entity.SeatInfo;
import com.air_force.entity.TicketInfo;
import com.air_force.mapper.AirplaneMapper;
import com.air_force.querydsl.AirplaneQueryDSL;
import com.air_force.repository.AirplaneRepository;
import com.air_force.repository.SeatInfoRepository;
import com.air_force.repository.TicketInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirplaneService {

    private final AirplaneMapper airplaneMapper = AirplaneMapper.INSTANCE;

    private final AirplaneQueryDSL airplaneQueryDSL;
    private final AirplaneRepository airplaneRepository;
    private final SeatInfoRepository seatInfoRepository;
    private final TicketInfoRepository ticketInfoRepository;


    public String AirplaneCreate(AirplaneDTO dto){
        int startDate = Integer.parseInt(dto.getRaceMonthStartDate());
        int endDate = Integer.parseInt(dto.getRaceMonthEndDate());

        Airplane airplane = new Airplane();
        airplane.setPrice(dto.getPrice());
        airplane.setAirline(dto.getAirline());
        airplane.setRaceMonth(dto.getRaceMonth());
        airplane.setDepartCity(dto.getDepartCity());
        airplane.setArriveCity(dto.getArriveCity());
        airplane.setDepartTime(dto.getDepartTime());
        airplane.setArriveTime(dto.getArriveTime());
        airplaneRepository.save(airplane);
        while(startDate <= endDate){

            TicketInfo ticketInfo = new TicketInfo();
            ticketInfo.setDate(String.valueOf(startDate));
            airplane.add(ticketInfo);
            ticketInfoRepository.save(ticketInfo);

            for(String seatName : WebConstants.SEAT_NAME){
                int number = WebConstants.SEAT_NUMBER;
                while(number <= WebConstants.SEAT_LIMIT){

                    SeatInfo seatInfo = new SeatInfo();
                    seatInfo.setSeatName(seatName + number);
                    seatInfo.setStatus(SeatInfo.Status.Y);
                    ticketInfo.add(seatInfo);
                    seatInfoRepository.save(seatInfo);

                    number ++ ;
                }
            }

            startDate ++ ;
        }

        return WebConstants.OK;
    }

    public List<String> AirlineRead(){
        return airplaneQueryDSL.findDistinctAirline();
    }

    public List<Airplane> AirLineRaceMonthRead(String airline){
        return airplaneQueryDSL.findByAirlineRaceMonth(airline);
    }

    public AirlineDTO AirplaneRead(String airline, String month){

        Airplane airplane = airplaneQueryDSL.findByAirline(airline,month);

        List<DateInfoDTO> dateInfoDTOS = new ArrayList<>();

        for(TicketInfo ticketInfo : airplane.getTicketInfos()){

            DateInfoDTO dto = new DateInfoDTO();
            dto.setDate(ticketInfo.getDate());
            long seat = ticketInfo.getSeatInfos()
                    .stream()
                    .filter(seatInfo -> SeatInfo.Status.N.equals(seatInfo.getStatus()))
                    .count();
            dto.setStatus(WebConstants.SEAT_TOTAL == seat ? WebConstants.N : WebConstants.Y);
            dateInfoDTOS.add(dto);

        }

        return airplaneMapper.toAirlineDTO(
                airplane.getAirline(),
                dateInfoDTOS);
    }

    public SeatInfoDTO SeatInfo(String airline, String month, String date){

        List<SeatInfo> seatInfos = airplaneQueryDSL.findBySeat(airline,month,date);

        return SeatInfoDTOMapper.DTOMapper(
                seatInfos.stream()
                        .filter(seatInfo -> SeatInfo.Status.Y.equals(seatInfo.getStatus()))
                        .count(),
                seatInfos.get(0).getTicketInfo().getAirplane().getPrice(),
                WebConstants.SEAT_LIMIT,
                seatInfos);
    }
    public List<String> DepartCity(String airline){
        return airplaneQueryDSL.findDistinctDepartCity(airline);
    }
    public List<String> ArriveCity(String airline, String departCity){
        return airplaneQueryDSL.findDistinctArriveCity(airline, departCity);
    }
    public List<SeatByCityDTO> RaceMonthAndDate(String airline, String departCity, String arriveCity){

        return airplaneQueryDSL.findByDepartCityAndArriveCity(airline,departCity,arriveCity)
                .stream()
                .map(airplane -> airplaneMapper.toSeatByCityDTO(
                        airplane.getAirline(),
                        airplane.getRaceMonth(),
                        airplane.getTicketInfos()
                )).collect(Collectors.toList());
    }




}
