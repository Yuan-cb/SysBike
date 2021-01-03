package com.bean;

import java.util.Date;

/**
 * @description:维护信息
 * @author: Yuan
 * @time: 2020/12/31 16:57
 */
public class Maintain {
    private Integer maintain_Id;
    private Integer bike_Id;
    private Integer maintain_price;
    private String break_reason;
    private Date maintain_Date;

    public Maintain() {
    }

    public Maintain(Integer maintain_Id, Integer bike_Id, Integer maintain_price, String break_reason, Date maintain_Date) {
        this.maintain_Id = maintain_Id;
        this.bike_Id = bike_Id;
        this.maintain_price = maintain_price;
        this.break_reason = break_reason;
        this.maintain_Date = maintain_Date;
    }

    public Integer getMaintain_Id() {
        return maintain_Id;
    }

    public void setMaintain_Id(Integer maintain_Id) {
        this.maintain_Id = maintain_Id;
    }

    public Integer getBike_Id() {
        return bike_Id;
    }

    public void setBike_Id(Integer bike_Id) {
        this.bike_Id = bike_Id;
    }

    public Integer getMaintain_price() {
        return maintain_price;
    }

    public void setMaintain_price(Integer maintain_price) {
        this.maintain_price = maintain_price;
    }

    public String getBreak_reason() {
        return break_reason;
    }

    public void setBreak_reason(String break_reason) {
        this.break_reason = break_reason;
    }

    public Date getMaintain_Date() {
        return maintain_Date;
    }

    public void setMaintain_Date(Date maintain_Date) {
        this.maintain_Date = maintain_Date;
    }

    @Override
    public String toString() {
        return "Maintain{" +
                "maintain_Id=" + maintain_Id +
                ", bike_Id=" + bike_Id +
                ", maintain_price=" + maintain_price +
                ", break_reason='" + break_reason + '\'' +
                ", maintain_Date=" + maintain_Date +
                '}';
    }
}
