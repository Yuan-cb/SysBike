package com.dao;

import com.bean.User;
import com.utils.DButils;

import java.util.List;

/**
 * @description:用户数据库操作
 * @author:Yuan
 * @time: 2020/12/28 9:58
 */
public class UserDao {

    /**
     * @Description:用户名查询
     * @Param: [name]
     * @return: com.bean.User
     * @Author: Yuan
     * @Date: 2020/12/29
     */
    public User getUserByName(String name) {

        String sql = "select * from user where name=?";
        return DButils.searchDate(User.class, sql, name);
    }
    /**
     * @Description: 获取所有用户信息
     * @Param: [sql]
     * @return: java.util.List<com.bean.User>
     * @Author: Yuan
     * @Date: 2020/12/30
     */
    public static List<User> getUserListByPage(String sql) {

        return DButils.searchDates(User.class, sql);
    }

    /**
     * @Description:电话查询
     * @Param: [phone]
     * @return: com.bean.User
     * @Author: Yuan
     * @Date: 2020/12/29
     */
    public User getUserByPhone(String phone) {

        String sql = "select * from user where phone=?";
        return DButils.searchDate(User.class, sql, phone);
    }

    /**
     * @Description: email查询
     * @Param: [email]
     * @return: com.bean.User
     * @Author: Yuan
     * @Date: 2020/12/29
     */
    public User getUserByEmail(String email) {
        String sql = "select * from user where email=?";
        return DButils.searchDate(User.class, sql, email);
    }


    public boolean saveUser(User user) {
        String sql = "insert into user(name, email, password, phone, creat_date, role) values(?,?,?,?,?,?)";
        return DButils.addDate(sql, user.getName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getCreat_Date(), user.getRole());
    }

    public List<User> getUserCount() {
        String sql = "select * from user";
        return DButils.searchDates(User.class, sql);
    }

    public User getUserById(String id) {
        String sql = "select * from user where user_Id=?";
        return DButils.searchDate(User.class, sql, id);
    }

    public boolean updateUser(User user) {
        String sql = "update user set name = ?,email = ?,password = ?,phone = ?,role = ? where user_Id = ?";
        return DButils.updateDate(sql, user.getName(), user.getEmail(), user.getPassword(),
                user.getPhone(), user.getRole(), user.getUser_Id());
    }

    public boolean deleteUserById(Integer id) {
        String sql = "delete from user where user_Id = ?";
        return DButils.removeDate(sql, id);

    }
}
