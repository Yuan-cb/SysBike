package com.servlet;

import com.bean.PageBean;
import com.bean.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //1、每页多少行 pageSize
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = null;//每页多少行
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.valueOf(pageSizeStr);
        } else {
            pageSize = 12;
        }
        //2、当前页数 currentPage
        String currentPageStr = request.getParameter("currentPage");
        Integer currentPage = null;//当前页数
        if (currentPageStr!=null && currentPageStr.length()>0){
            currentPage = Integer.valueOf(currentPageStr);
        }else{
            currentPage = 1;
        }
        //3、总数据行数 totalRows
        Integer totalRows = 0;
        UserDao userDao = new UserDao();
        totalRows = userDao.getUserCount().size();

        //5、起始行 startRow
        Integer startRow = (currentPage-1)*pageSize;

        StringBuffer sqlRow = new StringBuffer("SELECT * FROM user");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

        //把对应用户信息查询出来
        List<User> UserList = UserDao.getUserListByPage(sqlRow.toString());

        //页数信息封装
        PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, UserList);
        request.setAttribute("pageBean", pageBean);

        //修改信息
        String msg = (String) request.getAttribute("msg");
        request.setAttribute("msg", msg);
        //请求转发
        request.getRequestDispatcher("userListPage.jsp").forward(request, response);
    }

}
