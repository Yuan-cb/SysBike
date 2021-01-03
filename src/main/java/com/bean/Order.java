package com.bean;

import java.util.Date;

/**
 * @description:订单
 * @author: Yuan
 * @time: 2020/12/31 17:02
 */
public class Order {
    private Integer order_Id;
    private Integer user_Id;
    private Integer bike_Id;
    private Date order_Date;
    private String order_state;

    public Order() {
    }

    public Order(Integer order_Id, Integer user_Id, Integer bike_Id, Date order_Date, String order_state) {
        this.order_Id = order_Id;
        this.user_Id = user_Id;
        this.bike_Id = bike_Id;
        this.order_Date = order_Date;
        this.order_state = order_state;
    }

    public Integer getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(Integer order_Id) {
        this.order_Id = order_Id;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public Integer getBike_Id() {
        return bike_Id;
    }

    public void setBike_Id(Integer bike_Id) {
        this.bike_Id = bike_Id;
    }

    public Date getOrder_Date() {
        return order_Date;
    }

    public void setOrder_Date(Date order_Date) {
        this.order_Date = order_Date;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_Id +
                ", user_Id=" + user_Id +
                ", bike_Id=" + bike_Id +
                ", order_Date=" + order_Date +
                ", order_state='" + order_state + '\'' +
                '}';
    }
}
