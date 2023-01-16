package com.ldm.order.controller;

import com.ldm.order.common.ResultJSONObject;
import com.ldm.order.dao.FilmRepository;
import com.ldm.order.dao.ScheduleRepository;
import com.ldm.order.domain.Film;
import com.ldm.order.domain.Order;
import com.ldm.order.domain.Schedule;
import com.ldm.order.utils.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScheduleController {


    final private DataHelper dataHelper;
    private final ScheduleRepository scheduleRepository;
    private final FilmRepository filmRepository;

    public ScheduleController(DataHelper dataHelper,
                              ScheduleRepository scheduleRepository,
                              FilmRepository filmRepository) {
        this.dataHelper = dataHelper;
        this.scheduleRepository = scheduleRepository;
        this.filmRepository = filmRepository;
    }

    @GetMapping("/schedules")
    public ResultJSONObject scheduleList() {

//        List<Schedule> scheduleList = dataHelper.getScheduleList();
//        List<Map<String, Object>> res = new ArrayList<>();
//        for (Schedule schedule:scheduleList) {
//            Map<String, Object> scheduleInfo = new HashMap<>();
//            scheduleInfo.put("scheduleId", schedule.getId());
//            scheduleInfo.put("fid", schedule.getFid());
//            scheduleInfo.put("theater", schedule.getTheater());
//            scheduleInfo.put("name", dataHelper.getFilmById(schedule.getId()).getName());
//            scheduleInfo.put("showtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(schedule.getShowtime()));
//            scheduleInfo.put("quota", schedule.getQuota());
//            scheduleInfo.put("price", schedule.getPrice());
//            res.add(scheduleInfo);
//        }

        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<Map<String, Object>> res = new ArrayList<>();
        for (Schedule schedule: scheduleList) {
            Map<String, Object> map = new HashMap<>();
            Film film = filmRepository.findById(schedule.getFid()).get();
            String name = film.getName();
            map.put("id", schedule.getId());
            map.put("name", name);
            map.put("price", schedule.getPrice());
            map.put("quota", schedule.getQuota());
            map.put("theater", schedule.getTheater());
            res.add(map);
        }

        return ResultJSONObject.success(res);
        
    }


}

