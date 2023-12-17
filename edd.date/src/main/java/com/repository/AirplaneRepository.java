package com.air_force.repository;

import com.air_force.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane,Long> {
}
