package com.air_force.repository;

import com.air_force.entity.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketInfoRepository extends JpaRepository<TicketInfo,Long> {
}
