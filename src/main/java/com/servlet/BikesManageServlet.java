package com.servlet;

import com.bean.Bike;
import com.dao.BikeDao;
import com.dao.MaintainDao;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BikesManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String manage = request.getParameter("manage");
        //添加
        if(manage == "add" || "add".equals(manage)){
            request.setAttribute("manage", "添 加");
            request.getRequestDispatcher("BikesManage.jsp").forward(request, response);
            return;
        }
        //修改
        if(manage == "update" || "update".equals(manage)){
            String id = request.getParameter("bike_Id");
            if(id != null || !"".equals(id)){
                //数据库id查询
                BikeDao bikeDao = new BikeDao();
                Bike bike = bikeDao.getUserById(id);
                if(bike != null){
                    request.setAttribute("bike", bike);
                    request.setAttribute("manage", "修 改");
                    request.getRequestDispatcher("BikesManage.jsp").forward(request, response);
                }else{
                    request.setAttribute("msg", "id不存在！");
                    request.getRequestDispatcher("BikesShowServlet").forward(request, response);
                }
            }
            return;
        }
        //删除用户
        if(manage == "delete" || "delete".equals(manage)){
            String id = request.getParameter("bike_Id");
            //数据库删除
            BikeDao bikeDao = new BikeDao();
            boolean bool = bikeDao.deleteBikeById(id);
            if(bool)
                request.setAttribute("msg", "删除成功!");
            else
                request.setAttribute("msg", "id不存在！");
            request.getRequestDispatcher("AdminBikesShowServlet").forward(request, response);
            return;
        }
    }
}
