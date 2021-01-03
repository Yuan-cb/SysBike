package com.dao;

import com.bean.Maintain;
import com.utils.DButils;
import java.util.List;

/**
 * @description:
 * @author: Yuan
 * @time: 2020/12/31 17:09
 */
public class MaintainDao {

    public static List<Maintain> getMaintainListByPage(String sql) {
        return DButils.searchDates(Maintain.class, sql);
    }

    public static List<Maintain> getMaintainListByKey(String sql, String value) {
        return DButils.searchDates(Maintain.class, sql, value);
    }

    public List<Maintain> getMaintainCount(String key, String value) {
        String sql = "select * from bike_maintain";
        if(key != null){
            sql += " where " + key + "=?";
            return DButils.searchDates(Maintain.class, sql, value);
        }
        return DButils.searchDates(Maintain.class, sql);
    }

    public boolean saveMaintain(Maintain maintain) {
        String sql = "insert into bike_maintain(bike_Id, maintain_price, break_reason, maintain_Date) values(?,?,?,?)";
        return DButils.addDate(sql, maintain.getBike_Id(), maintain.getMaintain_price(), maintain.getBreak_reason(), maintain.getMaintain_Date());
    }

    public Maintain getMaintainByBike(String bike_Id) {
        String sql = "select * from bike_maintain where bike_Id=?";
        return DButils.searchDate(Maintain.class, sql, bike_Id);

    }

    public boolean deleteMaintain(String id) {
        String sql = "delete from bike_maintain where bike_id=?";
        return DButils.removeDate(sql ,id);
    }

}
