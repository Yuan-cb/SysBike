package com.servlet;

import com.bean.Bike;
import com.dao.BikeDao;
import com.dao.MaintainDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/MaintainManageServlet")
public class MaintainManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String manage = request.getParameter("manage");

        BikeDao bikeDao = new BikeDao();
        MaintainDao maintainDao = new MaintainDao();
        //返回
        if(manage == "back" || "back".equals(manage)){
            String id = request.getParameter("bike_Id");
            if(id != null || !"".equals(id)){
                //数据库单车状态修改
                boolean bool_bike = bikeDao.updateBikeState("空闲", id);//修改单车状态
                boolean bool_maintain = maintainDao.deleteMaintain(id);

                if(bool_bike && bool_maintain){
                    request.setAttribute("msg", "维护完成!");
                }else{
                    request.setAttribute("msg", "id不存在！");
                }
                request.getRequestDispatcher("MaintainShowServlet").forward(request, response);
            }
            return;
        }
        //销毁
        if(manage == "delete" || "delete".equals(manage)){
            String id = request.getParameter("bike_Id");
            //数据库删除
            boolean bool = maintainDao.deleteMaintain(id);
            if(bool) {
                boolean bool_bike = bikeDao.deleteBikeById(id);
                if (bool_bike)
                    request.setAttribute("msg", "销毁成功!");
            }
            else
                request.setAttribute("msg", "id不存在！");
            request.getRequestDispatcher("MaintainShowServlet").forward(request, response);
            return;
        }
    }
}
