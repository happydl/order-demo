package com.ldm.order.dao;

import com.ldm.order.domain.StatisticData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticDataRepository extends JpaRepository<StatisticData, Integer> {
}
