package com.servlet;

import com.bean.Order;
import com.dao.BikeDao;
import com.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/OrderManageServlet")
public class OrderManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        OrderDao orderDao = new OrderDao();
        Order order = null;

        String manage = request.getParameter("manage");
        String order_Id = request.getParameter("order_Id");

        if(manage == "back" || "back".equals(manage)){//结算
            //id查找
            order = orderDao.getOrderByOrder(order_Id);
            if(order != null){
                if(order.getOrder_state() == "已付"){
                    request.setAttribute("msg", "该订单已付");
                }else{
                    //结算
                    boolean bool = orderDao.updateState(order_Id, "已付");
                    if(bool){
                        request.setAttribute("msg", "结算成功！");
                        BikeDao bikeDao = new BikeDao();
                        bikeDao.updateBikeState("空闲", order.getBike_Id().toString());
                    }else {
                        request.setAttribute("msg", "结算失败");
                    }
                }
            }else{
                request.setAttribute("msg", "该订单不存在");
            }
            request.getRequestDispatcher("OrderShowServlet").forward(request, response);
            return;
        }
        //删除订单
        if(manage == "delete" || "delete".equals(manage)){
            //id查找
            order = orderDao.getOrderByOrder(order_Id);
            if(order != null){
                if(order.getOrder_state() == "未付" || "未付".equals(order.getOrder_state())){
                    request.setAttribute("msg", "对不起，该订单未结算，无法删除！");
                    request.getRequestDispatcher("OrderShowServlet").forward(request, response);
                    return;
                }else{
                    //数据库删除
                    boolean bool = orderDao.deleteOrder(order_Id);
                    request.setAttribute("msg", "删除成功!");
                }
            }else
                request.setAttribute("msg", "该订单不存在！");
            request.getRequestDispatcher("OrderShowServlet").forward(request, response);
            return;
        }
    }
}
