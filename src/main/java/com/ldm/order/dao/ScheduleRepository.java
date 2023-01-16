package com.ldm.order.dao;

import com.ldm.order.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByIdNotNull();
    List<Schedule> findAll();
}
