package com.ldm.order.controller;

import com.ldm.order.common.RespCode;
import com.ldm.order.common.ResultJSONObject;
import com.ldm.order.dao.FilmRepository;
import com.ldm.order.dao.OrderRepository;
import com.ldm.order.dao.ScheduleRepository;
import com.ldm.order.domain.Film;
import com.ldm.order.domain.Order;
import com.ldm.order.domain.Schedule;
import com.ldm.order.domain.User;
import com.ldm.order.service.OrderEvent;
import com.ldm.order.service.UserService;
import com.ldm.order.utils.CacheUtils;
import com.ldm.order.utils.DataHelper;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private DataHelper dataHelper;
    @Autowired
    private UserService userService;

    @Autowired
    private OrderEvent orderEvent;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @PostMapping("/orders")
    public ResultJSONObject placeOrder(@RequestBody @Validated Order order) {

        System.out.println("place order");

        String token = CacheUtils.getCurrentToken();
        Integer uid = userService.tokenToUser(token).getId();
        Schedule schedule = dataHelper.getScheduleById(order.getScheduleId());

        Order newOrder = new Order(uid, order.getScheduleId(), new Date(), order.getQuantity(), schedule.getPrice() * order.getQuantity());

        orderEvent.notifyObservers(newOrder);

//        dataHelper.addOrder(newOrder);
        orderRepository.save(newOrder);

        return ResultJSONObject.success();
    }

    @GetMapping("/orders")
    public ResultJSONObject listOrders() {

        String token = CacheUtils.getCurrentToken();
        Integer uid = userService.tokenToUser(token).getId();

//        List<Order> orders = dataHelper.getOrderList();
        List<Order> orders = orderRepository.findByCustomerId(uid);

//        List<Order> res = new ArrayList<>();
        List<Map<String, Object>> res = new ArrayList<>();
        for (Order order: orders) {
            Map<String, Object> map = new HashMap<>();
            Schedule schedule = scheduleRepository.findById(order.getScheduleId()).get();
            Film film = filmRepository.findById(schedule.getFid()).get();
            String name = film.getName();
            map.put("id", order.getId());
            map.put("name", name);
            map.put("orderTime", order.getOrderTime());
            map.put("quantity", order.getQuantity());
            map.put("price", order.getPrice());
            res.add(map);
        }

        return ResultJSONObject.success(res);
    }

}
