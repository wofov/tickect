package com.air_force.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"ticketInfo"})
public class SeatInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seatInfoSeq;

    private String seatName;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status{
        Y,N
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ticketInfo")
    private TicketInfo ticketInfo;

}
