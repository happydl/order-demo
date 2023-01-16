package com.ldm.order.controller;

import com.ldm.order.common.ResultJSONObject;
import com.ldm.order.dao.FilmRepository;
import com.ldm.order.dao.ScheduleRepository;
import com.ldm.order.dao.StatisticDataRepository;
import com.ldm.order.domain.Film;
import com.ldm.order.domain.Schedule;
import com.ldm.order.domain.StatisticData;
import com.ldm.order.utils.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController {

    @Autowired
    private DataHelper dataHelper;
    @Autowired
    private StatisticDataRepository statisticDataRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/statistics")
    public ResultJSONObject getStatistics() {



//        List<StatisticData> statisticDataList = dataHelper.getStatisticDataList();
//        List<Map<String, Object>> res = new ArrayList<>();
//        for (StatisticData data: statisticDataList) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("scheduleId", data.getScheduleId());
//            int fid = dataHelper.getScheduleById(data.getScheduleId()).getFid();
//            String name = dataHelper.getFilmById(fid).getName();
//            map.put("name", name);
//            map.put("quantity", data.getQuantity());
//            map.put("sales", data.getSales());
//            res.add(map);
//        }


        List<StatisticData> statisticDataList = statisticDataRepository.findAll();
        List<Map<String, Object>> res = new ArrayList<>();
        for (StatisticData data: statisticDataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("scheduleId", data.getScheduleId());
            Schedule schedule = scheduleRepository.findById(data.getScheduleId()).get();
            Film film = filmRepository.findById(schedule.getFid()).get();
            String name = film.getName();
            map.put("name", name);
            map.put("quantity", data.getQuantity());
            map.put("sales", data.getSales());
            res.add(map);
        }

        return ResultJSONObject.success(res);

    }


}
