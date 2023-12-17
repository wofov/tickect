package com.air_force.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long airplaneSeq;

    private String airline;//

    private String raceMonth;//

    private String departCity;
    private String arriveCity;
    private String departTime;
    private String arriveTime;
    private long price;

    @OneToMany(mappedBy = "airplane", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TicketInfo> ticketInfos = new ArrayList<>();

    public void add(TicketInfo ticketInfo){
        ticketInfo.setAirplane(this);
        getTicketInfos().add(ticketInfo);
    }



}