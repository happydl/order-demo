package com.ldm.order.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Order {

    private static int _id = 0;

    private Integer id;
    private Integer customerId;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    @NotNull
    private Integer scheduleId;
    private Date orderTime;

    @Min(1)
    @NotNull
    private Integer quantity;
    private Integer price;

    public Order(Integer customerId, Integer scheduleId, Date orderTime, Integer quantity, Integer price) {
        this.id = _id++;
        this.customerId = customerId;
        this.scheduleId = scheduleId;
        this.orderTime = orderTime;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
