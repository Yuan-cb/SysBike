package com.servlet;

import com.bean.User;
import com.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //注册用户
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String register = request.getParameter("register");//注册按钮
        String cancel = request.getParameter("cancel");//注册取消

        UserDao userDao = new UserDao();
        User user = null;
        //注册
        if(register != null){
            //封装User对象
            user = new User(null, name, email, password, phone, new Date(), "用户");
            //数据库添加
            boolean bool = userDao.saveUser(user);
            if(bool){
                alert(response, "注册成功！", "login.jsp");
                response.sendRedirect("login.jsp");//登录界面
            }else{
                //注册界面 请求转发
                alert(response, "注册失败", "register.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
                dispatcher.forward(request, response);
            }
        }else if(cancel != null){
            response.sendRedirect("login.jsp");//登录界面
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
    //输出流
    public static void alert(HttpServletResponse response, String msg, String url) throws IOException {
        PrintWriter out = response.getWriter();
        out.write("<script>");
        out.write(" alert(" + msg + ");");
        out.write("window.location.href = " + url);
        out.write("</script>");
        out.flush();
        out.close();
    }
}
