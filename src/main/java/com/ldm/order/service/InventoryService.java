package com.ldm.order.service;

import com.ldm.order.dao.ScheduleRepository;
import com.ldm.order.domain.Order;
import com.ldm.order.domain.Schedule;
import com.ldm.order.utils.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class InventoryService implements Observer {

    @Autowired
    private DataHelper dataHelper;
    private static final ExecutorService executorService =  Executors.newSingleThreadExecutor();
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void update(Observable o, Object arg) {
        Runnable runnable = ()-> {
            Order order = (Order) arg;
            Optional<Schedule> schedule = scheduleRepository.findById(order.getScheduleId());
            if (schedule.isPresent()) {
                Schedule s = schedule.get();
                s.setQuota(s.getQuota() - order.getQuantity());
                scheduleRepository.save(s);
            }
        };
        executorService.submit(runnable);
    }
}
