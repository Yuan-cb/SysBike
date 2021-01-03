package com.dao;

import com.bean.Bike;
import com.bean.Maintain;
import com.bean.Order;
import com.utils.DButils;

import javax.management.OperationsException;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: Yuan
 * @time: 2020/12/31 17:09
 */
public class OrderDao {
    public static boolean saveOrder(Order order) {
        String sql = "insert into bike_order(user_Id, bike_Id, order_Date, order_state) values(?,?,?,?)";
        return DButils.addDate(sql, order.getUser_Id(), order.getBike_Id(), order.getOrder_Date(), order.getOrder_state());
    }

    public static List<Order> getOrderListByKey(String sql, String value) {
        return DButils.searchDates(Order.class, sql ,value);
    }

    public static List<Order> getOrderListByPage(String sql) {
        return DButils.searchDates(Order.class, sql);
    }

    public static Order getOrderByUser_Id(Integer user_id) {
        String sql = "select * from bike_order where user_Id = ?";
        return DButils.searchDate(Order.class, sql, user_id);
    }

    public Order getOrderByBike(String bike_id) {
        String sql = "select * from bike_order where bike_Id=?";
        return DButils.searchDate(Order.class, sql, bike_id);
    }

    public List<Order> getOrderCount(String key, String value) {
        String sql = "select * from bike_order";
        if(key != null){
            sql += " where " + key + "=?";
            return DButils.searchDates(Order.class, sql, value);
        }
        return DButils.searchDates(Order.class, sql);
    }

    public Order getOrderByOrder(String order_id) {
        String sql = "select * from bike_order where order_id=?";
        return DButils.searchDate(Order.class, sql, order_id);
    }

    public boolean deleteOrder(String order_id) {
        String sql = "delete from bike_order where order_id=?";
        return DButils.removeDate(sql ,order_id);
    }

    public boolean updateState(String order_Id, String state) {
        String sql = "update bike_order set order_state=? where order_Id =?";
        return DButils.updateDate(sql, state, order_Id);
    }
}
