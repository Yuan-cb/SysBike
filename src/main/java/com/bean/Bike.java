package com.bean;

import java.util.Date;

/**
 * @description:单车
 * @author: Yuan
 * @time: 2020/12/31 16:51
 */
public class Bike {
    private Integer bike_Id;
    private String kind;
    private Integer bike_price;
    private String bike_state;
    private String address;
    private Date add_Date;

    public Bike() {
    }

    public Bike(Integer bike_Id, String kind, Integer bike_price, String bike_state, String address, Date add_Date) {
        this.bike_Id = bike_Id;
        this.kind = kind;
        this.bike_price = bike_price;
        this.bike_state = bike_state;
        this.address = address;
        this.add_Date = add_Date;
    }

    public Integer getBike_Id() {
        return bike_Id;
    }

    public void setBike_Id(Integer bike_Id) {
        this.bike_Id = bike_Id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getBike_price() {
        return bike_price;
    }

    public void setBike_price(Integer bike_price) {
        this.bike_price = bike_price;
    }

    public String getBike_state() {
        return bike_state;
    }

    public void setBike_state(String bike_state) {
        this.bike_state = bike_state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAdd_Date() {
        return add_Date;
    }

    public void setAdd_Date(Date add_Date) {
        this.add_Date = add_Date;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bike_Id=" + bike_Id +
                ", kind='" + kind + '\'' +
                ", bike_price=" + bike_price +
                ", bike_state='" + bike_state + '\'' +
                ", address='" + address + '\'' +
                ", add_date=" + add_Date +
                '}';
    }
}
