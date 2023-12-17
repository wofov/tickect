package com.air_force.querydsl;

import com.air_force.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AirplaneQueryDSL {

    private final JPAQueryFactory queryFactory;
    private final QAirplane airplane = QAirplane.airplane;
    private final QTicketInfo ticketInfo = QTicketInfo.ticketInfo;
    private final QSeatInfo seatInfo = QSeatInfo.seatInfo;

    public Airplane findByAirline(String airline,
                                  String month){
        BooleanBuilder whereClaus = new BooleanBuilder();
        whereClaus.and(airplane.airline.eq(airline));
        whereClaus.and(airplane.raceMonth.eq(month));

        return queryFactory
                .selectFrom(airplane)
                .where(whereClaus)
                .fetchFirst();
    }
    public List<String> findDistinctAirline(){
        return queryFactory
                .select(airplane.airline)
                .distinct()
                .from(airplane)
                .fetch();
    }
    public List<Airplane> findByAirlineRaceMonth(String airline){
        BooleanBuilder whereClaus = new BooleanBuilder();
        whereClaus.and(airplane.airline.eq(airline));

        return queryFactory
                .select(airplane)
                .from(airplane)
                .where(whereClaus)
                .fetch();
    }

    public List<SeatInfo> findBySeat(String airline, String month, String date){
        return queryFactory
                .select(seatInfo)
                .from(seatInfo)
                .join(seatInfo.ticketInfo, ticketInfo).fetchJoin()
                .join(ticketInfo.airplane, airplane).fetchJoin()
                .where(
                        airplane.airline.eq(airline),
                        airplane.raceMonth.eq(month),
                        ticketInfo.date.eq(date)
                )
                .fetch();
    }
    public List<String> findDistinctDepartCity(String airline){
        BooleanBuilder whereClaus = new BooleanBuilder();
        whereClaus.and(airplane.airline.eq(airline));

        return queryFactory
                .select(airplane.departCity)
                .distinct()
                .from(airplane)
                .where(whereClaus)
                .fetch();
    }
    public List<String> findDistinctArriveCity(String airline, String departCity){
        BooleanBuilder whereClaus = new BooleanBuilder();
        whereClaus.and(airplane.airline.eq(airline));
        whereClaus.and(airplane.departCity.eq(departCity));

        return queryFactory
                .select(airplane.arriveCity)
                .distinct()
                .from(airplane)
                .where(whereClaus)
                .fetch();
    }
    public List<Airplane> findByDepartCityAndArriveCity(String airline, String departCity,String arriveCity){

        return queryFactory
                .selectFrom(airplane)
                .where(
                        airplane.airline.eq(airline),
                        airplane.departCity.eq(departCity),
                        airplane.arriveCity.eq(arriveCity)
                )
                .fetch();
    }

}
