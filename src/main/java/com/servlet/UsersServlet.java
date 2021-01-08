package com.servlet;

import com.bean.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description: 用户添加、修改
 * @Param:
 * @return:
 * @Author: Yuan
 * @Date: 2020/12/31
 */
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String user_Id = request.getParameter("user_Id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");//角色
        String Button = request.getParameter("userButton");//按钮
        String cancel = request.getParameter("cancel");//取消

        UserDao userDao = new UserDao();
        User user = null;
        if(Button == "添 加" || "添 加".equals(Button)){
            //封装User对象
            if(role == "user" || "user".equals(role))
                user = new User(null, name, email, password, phone, new Date(), "用户");
            else if(role == "admin" || "admin".equals(role))
                user = new User(null, name, email, password, phone, new Date(), "管理员");
            //数据库添加
            boolean bool = userDao.saveUser(user);
            if(bool){
                request.setAttribute("msg", "添加成功");
                request.getRequestDispatcher("UsersShowServlet").forward(request, response);//请求转发
            }else{
//                发送失败信息
                request.setAttribute("msg", "添加失败！");
                request.getRequestDispatcher("UsersManage.jsp").forward(request, response);
            }
            return;
        }
        if(cancel != null){
            response.sendRedirect("UsersShowServlet");//用户列表界面
            return;
        }
        if(Button == "修 改" || "修 改".equals(Button)){
            if(user_Id != null){
                //封装User对象
                if(role == "user" || "user".equals(role))
                    user = new User(Integer.valueOf(user_Id), name, email, password, phone, null, "用户");
                else if(role == "admin" || "admin".equals(role))
                    user = new User(Integer.valueOf(user_Id), name, email, password, phone, null, "管理员");
                boolean bool = userDao.updateUser(user);
                if (bool){
                    request.setAttribute("msg", "修改成功");
                    request.getRequestDispatcher("UsersShowServlet").forward(request, response);//请求转发
                }else{
                    request.setAttribute("msg", "修改失败");
                    request.getRequestDispatcher("UsersManage.jsp").forward(request, response);//请求转发
                }
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
