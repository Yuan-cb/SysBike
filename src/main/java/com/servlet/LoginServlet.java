package com.servlet;

import com.bean.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        String register = request.getParameter("register");

        if(login != null){
            if(email == "" || email == null){
                request.setAttribute("show1", "用户名不存在！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                //用户查询
                UserDao userDao = new UserDao();
                User user = userDao.getUserByEmail(email);
                //邮箱未注册
                if (user == null){
                    request.setAttribute("show1", "该邮箱未注册！");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }else{//密码核对
                    if(user.getPassword().equals(password)){
                        //用户对象存入session
                        request.getSession().setAttribute("session_user", user);
                        //根据角色进入不同界面
                        if(user.getRole().equals("管理员"))
                            response.sendRedirect("adminMainPage.jsp");
                        else if(user.getRole().equals("用户"))
                            response.sendRedirect("userMainPage.html");
                    }else{
                        request.setAttribute("show2", "密码错误！");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }
            //请求转发，转注册页面
        }else if(register != null){
            //重定向，转注册页面
            response.sendRedirect("register.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //doPost(request, response);
    }
}
