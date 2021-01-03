package com.servlet;

import com.bean.Bike;
import com.bean.Maintain;
import com.bean.PageBean;
import com.bean.User;
import com.dao.BikeDao;
import com.dao.MaintainDao;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/MaintainShowServlet")
public class MaintainShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //单车查询
        String key = request.getParameter("key");
        String value = request.getParameter("value");

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
        MaintainDao maintainDao = new MaintainDao();
        totalRows = maintainDao.getMaintainCount(key, value).size();//单车id查询

        //5、起始行 startRow
        Integer startRow = (currentPage-1)*pageSize;

        //把所有用户信息查询出来
        StringBuffer sqlRow = new StringBuffer("SELECT * FROM bike_maintain");
        List<Maintain> MaintainList = null;
        if(key != null){//查找
            sqlRow.append(" where ").append(key).append("=?");
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
            MaintainList = MaintainDao.getMaintainListByKey(sqlRow.toString(), value);
            //标记
            request.setAttribute("key", key);
            request.setAttribute("value", value);
        }else {
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
            MaintainList = MaintainDao.getMaintainListByPage(sqlRow.toString());
        }

        //页数信息封装
        PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, MaintainList);
        request.setAttribute("pageBean", pageBean);

        //修改信息
        String msg = (String) request.getAttribute("msg");
        request.setAttribute("msg", msg);
        //请求转发
        request.getRequestDispatcher("MaintainListPage.jsp").forward(request, response);
    }
}
