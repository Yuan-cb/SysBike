package com.servlet;

import com.bean.Bike;
import com.bean.PageBean;
import com.dao.BikeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminBikesShowServlet extends HttpServlet {
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
        Bike bike = new Bike();
        BikeDao bikeDao = new BikeDao();
        totalRows = bikeDao.getBikeCount(key, value).size();

        //5、起始行 startRow
        Integer startRow = (currentPage-1)*pageSize;

        StringBuffer sqlRow = new StringBuffer("SELECT * FROM bike_info");
        //把所有用户信息查询出来
        List<Bike> BikeList = null;
        if(key != null){//单车查找
            sqlRow.append(" where ").append(key).append("=?");
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
            BikeList = BikeDao.getBikeListByKey(sqlRow.toString(), value);
            //标记
            request.setAttribute("key", key);
            request.setAttribute("value", value);
        }else {
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);
            BikeList = BikeDao.getBikeListByPage(sqlRow.toString());
        }

        //页数信息封装
        PageBean pageBean = new PageBean(currentPage, pageSize, totalRows, BikeList);
        request.setAttribute("pageBean", pageBean);

        //修改信息
        String msg = (String) request.getAttribute("msg");
        request.setAttribute("msg", msg);

        String flag = request.getParameter("flag");
        String flag1 = (String) request.getAttribute("flag");
        if("rent".equals(flag) || "rent".equals(flag1)){
            request.setAttribute("flag", "rent");
            request.getRequestDispatcher("rentBike.jsp").forward(request, response);
            return;
        }
        //请求转发
        request.getRequestDispatcher("bikeListPage.jsp").forward(request, response);
    }
}
