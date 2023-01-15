package com.ldm.order.domain;

import java.util.Date;

public class Schedule {

    private Integer id;
    private Integer fid;
    private String theater;
    private Date showtime;
    private Integer quota;
    private Integer price;

    public Schedule(Integer id, Integer fid, String theater, Date showtime, Integer quota, Integer price) {
        this.id = id;
        this.fid = fid;
        this.theater = theater;
        this.showtime = showtime;
        this.quota = quota;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Integer getFid() {
        return fid;
    }

    public String getTheater() {
        return theater;
    }

    public Date getShowtime() {
        return showtime;
    }

    public Integer getQuota() {
        return quota;
    }

    public Integer getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
