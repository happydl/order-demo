package com.ldm.order.service;

import com.ldm.order.dao.StatisticDataRepository;
import com.ldm.order.domain.Order;
import com.ldm.order.domain.StatisticData;
import com.ldm.order.utils.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class StatisticDataService implements Observer {

    @Autowired
    private DataHelper dataHelper;

    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();
    @Autowired
    private StatisticDataRepository statisticDataRepository;

    @Override
    public void update(Observable o, Object arg) {
        Runnable runnable = () -> {
            Order order = (Order) arg;
            Optional<StatisticData> opData = statisticDataRepository.findById(order.getScheduleId());
            StatisticData data = opData.orElseGet(() -> new StatisticData(order.getScheduleId()));
            data.setQuantity(data.getQuantity() + order.getQuantity());
            data.setSales(data.getSales() + order.getPrice());
            statisticDataRepository.save(data);
//            dataHelper.doStatistics(order);
        };
        executorService.submit(runnable);
    }
}
