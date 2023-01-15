package com.ldm.order.service;

import com.ldm.order.domain.Order;
import com.ldm.order.utils.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class StatisticDataService implements Observer {

    @Autowired
    private DataHelper dataHelper;

    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();

    @Override
    public void update(Observable o, Object arg) {
        Runnable runnable = () -> {
            Order order = (Order) arg;
            dataHelper.doStatistics(order);
        };
        executorService.submit(runnable);
    }
}
