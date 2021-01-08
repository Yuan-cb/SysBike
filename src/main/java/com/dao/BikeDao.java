package com.dao;

import com.bean.Bike;
import com.utils.DButils;

import java.util.List;

/**
 * @description:
 * @author: Yuan
 * @time: 2020/12/31 17:08
 */
public class BikeDao {
    /**
     * @Description: 页面查询
     * @Param: [sql]
     * @return: java.util.List<com.bean.Bike>
     * @Author: Yuan
     * @Date: 2021/1/8
     */
    public static List<Bike> getBikeListByPage(String sql) {

        return DButils.searchDates(Bike.class, sql);
    }
    /**
     * @Description: 更新单车信息
     * @Param: [bike]
     * @return: boolean
     * @Author: Yuan
     * @Date: 2021/1/8
     */
    public static boolean updateBike(Bike bike) {

        String sql = "update bike_info set kind = ?,bike_price = ?,bike_state = ?" +
                ",address = ? where bike_Id = ?";
        return DButils.updateDate(sql, bike.getKind(), bike.getBike_price(), bike.getBike_state(),
                bike.getAddress(), bike.getBike_Id());
    }

    public static List<Bike> getBikeListByKey(String sql, String value) {
        return DButils.searchDates(Bike.class, sql, value);
    }

    public Bike getBikeById(String id) {
        String sql = "select * from bike_info where bike_Id=?";
        return DButils.searchDate(Bike.class, sql, id);
    }

    public List<Bike> getBikeCount(String key, String value) {
        String sql = "select * from bike_info";
        if(key != null){
            sql += " where " + key + "=?";
            return DButils.searchDates(Bike.class, sql, value);
        }
        return DButils.searchDates(Bike.class, sql);
    }

    public boolean saveBike(Bike bike) {
        String sql = "insert into bike_info(kind, bike_price, bike_state, address, add_Date) values(?,?,?,?,?)";
        return DButils.addDate(sql, bike.getKind(), bike.getBike_price(), bike.getBike_state(),
                bike.getAddress(), bike.getAdd_Date());
    }

    public boolean deleteBikeById(String id) {
        String sql = "delete from bike_info where bike_Id = ?";
        return DButils.removeDate(sql, id);
    }

    public boolean updateBikeState(String state, String id) {
        String sql = "update bike_info set bike_state = ? where bike_Id = ?";
        return DButils.updateDate(sql, state, id);
    }
}
