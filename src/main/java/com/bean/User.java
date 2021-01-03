package com.bean;

import java.util.Date;

/**
 * @description:用户封装类
 * @author: Yuan
 * @time: 2020/12/28 9:59
 */
public class User {
    private Integer user_Id;//用户id
    private String name;//用户名
    private String email;//用户邮箱
    private String password;//用户密码
    private String phone;//电话
    private String role;
    private Date creat_Date;//注册时间


    public User() {
    }

    public User(Integer user_Id, String name, String email, String password, String phone, Date creat_Date, String role) {
        this.user_Id = user_Id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.creat_Date = creat_Date;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreat_Date() {
        return creat_Date;
    }

    public void setCreat_Date(Date creat_Date) {
        this.creat_Date = creat_Date;
    }
}
