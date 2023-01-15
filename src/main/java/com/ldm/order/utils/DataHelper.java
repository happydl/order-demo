package com.ldm.order.utils;

import com.ldm.order.common.RespCode;
import com.ldm.order.domain.Film;
import com.ldm.order.domain.Order;
import com.ldm.order.domain.Schedule;
import com.ldm.order.domain.StatisticData;
import com.ldm.order.exception.MyException;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class DataHelper {

    public List<Film> filmList = new ArrayList<>();
    public List<Schedule> scheduleList = new ArrayList<>();
    public List<Order> orderList = new ArrayList<>();
    public Map<Integer, StatisticData> statisticDataMap = new HashMap<>();

    public void addFilm(Film f1) {
        filmList.add(f1);
    }

    public  void addSchedule(Schedule s1) {
        scheduleList.add(s1);
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<StatisticData> getStatisticDataList() {
        return new ArrayList<>(statisticDataMap.values());
    }

    public Film getFilmById(int id) {
        for (Film f: filmList)
            if (f.getId() == id) {
                return f;
            }
        throw new MyException(RespCode.ERROR, "未找到id对应电影");
    }

    public Schedule getScheduleById(int id) {
        for (Schedule s:scheduleList) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new MyException(RespCode.ERROR, "未找到id对应排片");
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void doStatistics(Order order) {
        int scheduleId = order.getScheduleId();
        StatisticData data;
        if (statisticDataMap.containsKey(scheduleId)) {
            data = statisticDataMap.get(scheduleId);
        } else {
            data = new StatisticData(scheduleId);
            statisticDataMap.put(scheduleId, data);
        }
        data.setQuantity(data.getQuantity() + order.getQuantity());
        data.setSales(data.getSales() + order.getPrice());
    }
}
