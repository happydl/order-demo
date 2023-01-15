package com.ldm.order.service;

import com.ldm.order.domain.Order;
import javafx.beans.InvalidationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Observable;

@Service
public class OrderEvent extends Observable {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private StatisticDataService statisticDataService;

    @PostConstruct
    void init() {
        addObserver(inventoryService);
        addObserver(statisticDataService);
    }

    public void notifyObservers(Order order){
        super.setChanged();
        super.notifyObservers(order);
    }

}
