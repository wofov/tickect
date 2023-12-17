package com.air_force.repository;

import com.air_force.entity.SeatInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatInfoRepository extends JpaRepository<SeatInfo,Long> {
}
