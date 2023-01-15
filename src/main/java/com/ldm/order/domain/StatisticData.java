package com.ldm.order.domain;

public class StatisticData {


    private Integer scheduleId;
    private Integer quantity = 0;
    private Integer sales = 0; // 销售额

    public StatisticData(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
