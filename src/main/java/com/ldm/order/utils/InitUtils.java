package com.ldm.order.utils;

import com.ldm.order.domain.Film;
import com.ldm.order.domain.Schedule;
import com.ldm.order.service.UserService;
import com.ldm.order.utils.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.GregorianCalendar;

@Component
public class InitUtils {

    @Autowired
    private DataHelper dataHelper;

    @Autowired
    private UserService userService;


    @PostConstruct
    void init() {
        userService.createUser("jim", "123456");
        userService.createUser("tom", "654321");

        Film f1 = new Film(1, "喜羊羊和灰太狼");
        Film f2 = new Film(2, "西游记");

        dataHelper.addFilm(f1);
        dataHelper.addFilm(f2);

        Schedule s1 = new Schedule(1, 1, "theater 1",
                new GregorianCalendar(2023,1,13,8,15).getTime(),
                10,
                10);

        Schedule s2 = new Schedule(2,2,"theater 2",
                new GregorianCalendar(2023,1,13,9,0).getTime(),
                20,
                20);

        dataHelper.addSchedule(s1);
        dataHelper.addSchedule(s2);

    }
}
