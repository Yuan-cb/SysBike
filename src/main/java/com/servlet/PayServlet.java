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
import java.util.List;

@WebServlet(urlPatterns = "/PayServlet")
public class PayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        OrderDao orderDao = new OrderDao();
        String pay1 = request.getParameter("pay");

        //查找是否有订单
        User user = (User)request.getSession().getAttribute("session_user");
        Order order = OrderDao.searchOrder_state(user.getUser_Id().toString(), "未付");

        if(order == null){
            request.setAttribute("msg", "您还没有订单！");
            request.getRequestDispatcher("userMainPage.jsp").forward(request, response);
        }else if(order.getOrder_state().equals("未付")){
            request.setAttribute("order", order);
            BikeDao bikeDao = new BikeDao();
            Bike bike = bikeDao.getBikeById(order.getBike_Id().toString());
            //支付费用
            Date now = new Date();
            long hour = (now.getTime() - order.getOrder_Date().getTime()) / (1000 * 60 * 60);
            long pay = hour * bike.getBike_price() + 1;
            request.setAttribute("pay", pay);
            request.setAttribute("bike", bike);
            if(pay1 != null){
                request.setAttribute("msg", "支付成功");
                orderDao.updateState(order.getOrder_Id().toString(), "已付");
                request.getRequestDispatcher("userMainPage.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("payOrder.jsp").forward(request, response);
        }
    }
}
