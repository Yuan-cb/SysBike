package com.servlet;

import com.bean.Bike;
import com.bean.Maintain;
import com.bean.Order;
import com.bean.User;
import com.dao.BikeDao;
import com.dao.MaintainDao;
import com.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class BikesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String bike_Id = request.getParameter("bike_Id");
        String kind = request.getParameter("kind");
        String bike_price = request.getParameter("bike_price");
        String bike_state = request.getParameter("bike_state");
        String address = request.getParameter("address");
        String Button = request.getParameter("bikeButton");//按钮
        String cancel = request.getParameter("cancel");//取消

        BikeDao bikeDao = new BikeDao();
        MaintainDao maintainDao = new MaintainDao();
        OrderDao orderDao = new OrderDao();
        //封装User对象
        Bike bike = null;
        if (Button == "添 加" || "添 加".equals(Button)) {
            //数据库添加
            bike = new Bike(null, kind, Integer.valueOf(bike_price), bike_state, address, new Date());
            boolean bool = bikeDao.saveBike(bike);
            if (bool) {
                request.setAttribute("msg", "添加成功");
                request.getRequestDispatcher("AdminBikesShowServlet").forward(request, response);//请求转发
            } else {
//                发送失败信息
                request.setAttribute("msg", "添加失败！");
                request.getRequestDispatcher("BikesManage.jsp").forward(request, response);
            }
            return;
        }
        if (cancel != null) {
            response.sendRedirect("AdminBikesShowServlet");//用户列表界面
            return;
        }
        if (Button == "修 改" || "修 改".equals(Button)) {
            if (bike_Id != null) {
                //封装对象
                bike = new Bike(Integer.valueOf(bike_Id), kind, Integer.valueOf(bike_price), bike_state, address, null);
                boolean bool = BikeDao.updateBike(bike);
                if (bool) {
                    if("维护中".equals(bike_state) && maintainDao.getMaintainByBike(bike_Id) == null){
                        String maintain_price = request.getParameter("maintain_price");
                        String break_reason = request.getParameter("break_reason");
                        //封装
                        Maintain maintain = new Maintain(null, Integer.valueOf(bike_Id), Integer.valueOf(maintain_price), break_reason, new Date());
                        //添加维护列表
                        boolean b = maintainDao.saveMaintain(maintain);
                    }else if("已租借".equals(bike_state) && orderDao.getOrderByBike(bike_Id) == null){
                        Integer user_Id = ((User)request.getSession().getAttribute("session_user")).getUser_Id();
                        Order order = new Order(null, user_Id, Integer.valueOf(bike_Id), new Date(), "未付");
                        //数据库添加
                        boolean b = OrderDao.saveOrder(order);
                    }
                    request.setAttribute("msg", "修改成功");
                    request.getRequestDispatcher("AdminBikesShowServlet").forward(request, response);//请求转发
                } else {
                    request.setAttribute("msg", "修改失败");
                    request.getRequestDispatcher("BikesManage.jsp").forward(request, response);//请求转发
                }
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
