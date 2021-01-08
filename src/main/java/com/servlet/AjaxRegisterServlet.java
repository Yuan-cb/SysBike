package com.servlet;

import com.bean.Bike;
import com.bean.User;
import com.dao.BikeDao;
import com.dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String bikeId = request.getParameter("bike_Id");

        //数据库查询
        UserDao userDao = new UserDao();//jdbc
        ObjectMapper mapper = new ObjectMapper();//ajax
        //ajax响应
        if(name != null){
            //1.用户名查询
            User userByName = userDao.getUserByName(name);
            if(userByName == null){
                mapper.writeValue(response.getWriter(), true);
            }else{
                mapper.writeValue(response.getWriter(), false);
            }
            return;
        }
        if(email != null){
            //2.email查询
            User userByEmail = userDao.getUserByEmail(email);
            if(userByEmail == null){
                mapper.writeValue(response.getWriter(), true);
            }else{
                mapper.writeValue(response.getWriter(), false);
            }
            return;
        }
        if(phone != null){
            //电话查询
            User userByPhone = userDao.getUserByPhone(phone);
            if(userByPhone == null){
                mapper.writeValue(response.getWriter(), true);
            }else{
                mapper.writeValue(response.getWriter(), false);
            }
        }
        if (bikeId != null){
            BikeDao bikeDao = new BikeDao();
            Bike bike = bikeDao.getBikeById(bikeId);
            if (bike == null){
                mapper.writeValue(response.getWriter(), true);
            }else{
                mapper.writeValue(response.getWriter(), false);
            }
        }
    }
}
