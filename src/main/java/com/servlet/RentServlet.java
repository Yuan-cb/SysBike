package com.servlet;

import com.bean.Bike;
import com.bean.Order;
import com.bean.User;
import com.dao.BikeDao;
import com.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/RentServlet")
public class RentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String bikeId = request.getParameter("bike_Id");
        if (bikeId != null){
            //用户对象
            User user = (User)request.getSession().getAttribute("session_user");
            //订单查找
            OrderDao orderDao = new OrderDao();
            Order order = orderDao.getOrderByUser(user.getUser_Id());
            request.setAttribute("flag", "rent");
            if(order.getOrder_state().equals("未付")){
                request.setAttribute("msg", "您有订单未结算，无法租借！");
            }else{
                BikeDao bikeDao = new BikeDao();
                Bike bike = bikeDao.getBikeById(bikeId);
                if (bike == null){
                    request.setAttribute("msg", "该id单车不存在！");
                }else if(!bike.getBike_state().equals("空闲")){
                    request.setAttribute("msg", "该单车状态异常无法租借");
                }else{
                    Order order1 = new Order(null, user.getUser_Id(), bike.getBike_Id(), new Date(), "未付");
                    boolean bool = orderDao.saveOrder(order1);
                    if (bool){
                        request.setAttribute("msg", "租借成功");
                        bikeDao.updateBikeState("已租借", bike.getBike_Id().toString());
                    }
                    else
                        request.setAttribute("msg", "租借失败");
                }
            }
            request.getRequestDispatcher("AdminBikesShowServlet").forward(request, response);
        }
    }
}
