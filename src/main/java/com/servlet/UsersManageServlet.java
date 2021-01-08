package com.servlet;

import com.bean.Order;
import com.bean.User;
import com.dao.OrderDao;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String manage = request.getParameter("manage");
        String manage1 = (String) request.getAttribute("manage1");

        UserDao userDao = new UserDao();
        //添加用户
        if(manage == "add" || "add".equals(manage)){
            request.setAttribute("manage", "添 加");
            request.getRequestDispatcher("UsersManage.jsp").forward(request, response);
            return;
        }
        //修改用户
        if(manage == "update" || "update".equals(manage)){
            String id = request.getParameter("user_Id");
            if(id != null || !"".equals(id)){
                //数据库id查询
                User user = userDao.getUserById(id);
                if(user != null){
                    request.setAttribute("user", user);
                    request.setAttribute("manage", "修 改");
                    request.getRequestDispatcher("UsersManage.jsp").forward(request, response);
                }else{
                    request.setAttribute("msg", "id不存在！");
                    request.getRequestDispatcher("UsersShowServlet").forward(request, response);
                }
            }
            return;
        }
        //删除用户
        if(manage == "delete" || "delete".equals(manage)){
            String id = request.getParameter("user_Id");
            if(id != null || !"".equals(id)){
                //数据库删除
                boolean bool = userDao.deleteUserById(Integer.valueOf(id));
                if(bool)
                    request.setAttribute("msg", "删除成功!");
                else
                    request.setAttribute("msg", "id不存在！");
                request.getRequestDispatcher("UsersShowServlet").forward(request, response);
            }
            return;
        }
        //查询用户
        if("self".equals(manage) || "self".equals(manage1)){
            User userSearch = (User) request.getSession().getAttribute("session_user");
            Order orderByUser_id = OrderDao.searchOrder_state(userSearch.getUser_Id().toString(), "未付");
            request.setAttribute("userSearch", userSearch);
            if(orderByUser_id != null)
                request.setAttribute("order_Id", orderByUser_id.getOrder_Id());
            request.setAttribute("msg", request.getParameter("msg"));
            if("self".equals(manage))
                request.setAttribute("manage", manage);
            if("self".equals(manage1))
                request.setAttribute("manage", manage1);
            request.getRequestDispatcher("userMessage.jsp").forward(request, response);
            return;
        }
        if(manage == "search" || "search".equals(manage)){
            String msg = request.getParameter("search_msg");
            //name查询
            User userSearch = userDao.getUserByName(msg);
            if(userSearch == null){
                //邮箱查询
                userSearch = userDao.getUserByEmail(msg);
                if(userSearch == null) {
                    //电话查询
                    userSearch = userDao.getUserByPhone(msg);
                    if(userSearch == null){
                        request.setAttribute("msg", "用户不存在!");
                        request.getRequestDispatcher("UsersShowServlet").forward(request, response);
                    }
                }
            }
            //查询用户是否有未付订单
            Order orderByUser_id = OrderDao.searchOrder_state(userSearch.getUser_Id().toString(), "未付");
            request.setAttribute("userSearch", userSearch);
            if(orderByUser_id != null)
                request.setAttribute("order_Id", orderByUser_id.getOrder_Id());
            request.setAttribute("manage", manage);
            request.getRequestDispatcher("userMessage.jsp").forward(request, response);
            return;
        }
    }
}
