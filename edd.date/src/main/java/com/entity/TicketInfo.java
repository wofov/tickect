package com.air_force.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"airplane"})
public class TicketInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticketInfoSeq;

    private String date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "airplaneSeq")
    private Airplane airplane;

    @OneToMany(mappedBy = "ticketInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SeatInfo> seatInfos = new ArrayList<>();
    public void add(SeatInfo seatInfo){
        seatInfo.setTicketInfo(this);
        getSeatInfos().add(seatInfo);
    }

}
