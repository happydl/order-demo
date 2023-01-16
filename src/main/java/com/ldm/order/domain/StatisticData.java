package com.ldm.order.domain;

import javax.persistence.*;

@Entity
@Table(name="t_statistics", uniqueConstraints = @UniqueConstraint(name="un_statisticDataId", columnNames = {"scheduleId"}))
public class StatisticData {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer scheduleId;
    private Integer quantity = 0;
    private Integer sales = 0; // 销售额

    public StatisticData() {

    }

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
