package com.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Date;
import java.sql.*;
import java.util.*;

/*
 *@description:
 *@author:
 *@time: 2020/12/27 22:20
 */
public abstract class DButils {

    /**
     * @Description:返回数据库
     * @Param: null
     * @return: java.sql.Connection
     * @Author: Yuan
     * @Date: 2020/12/27
     */
    public static Connection getConnection() throws Exception {

        //读取配置文件
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");
        Properties properties = new Properties();
        properties.load(in);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        return dataSource.getConnection();
    }

    /**
     * @Description: 添加数据
     * @Param: sql（sql语句）, args(数据参数)
     * @return: boolean
     * @Author: Yuan
     * @Date: 2020/12/27
     */
    public static boolean addDate(String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;//判断是否添加成功

        try {
            //封装数据库
            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);
            //sql语句添加参数
            if (args!=null && args.length>0){
                for (int i=0; i<args.length; i++){

                    ps.setObject(i+1, args[i]);
                }
            }
            count = ps.executeUpdate();//返回添加记录数
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return count!=null && count>0 ? true:false;
    }

    /**
     * @Description: 删除数据
     * @Param: sql（sql语句）, args(数据参数)
     * @return: boolean
     * @Author: Yuan
     * @Date: 2020/12/27
     */
    public static boolean removeDate(String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;

        try {
            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args!=null && args.length>0){
                for (int i=0; i<args.length; i++){
                    ps.setObject(i+1, args[i]);
                }
            }
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return count!=null && count>0 ? true:false;
    }

    /**
     * @Description: 修改数据
     * @Param: sql（sql语句）, args(删除对象参数)
     * @return: boolean
     * @Author: Yuan
     * @Date: 2020/12/27
     */
    public static boolean updateDate(String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        Integer count = null;

        try {
            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);

            if (args!=null && args.length>0){
                for (int i=0; i<args.length; i++){
                    //判断Date类型
                    if (args[i] instanceof java.util.Date){
                        java.util.Date date = (Date) args[i];
                        //类型转换
                        args[i] = new java.sql.Date(date.getTime());
                    }

                    ps.setObject(i+1, args[i]);
                }
            }
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
        return count!=null && count>0 ? true:false;
    }

    /**
     * @Description: 查找单个数据
     * @Param: clazz(对象类), sql（sql语句）, args(删除对象参数)
     * @return: T（对象类）
     * @Author: Yuan
     * @Date: 2020/12/27
     */
    public static <T>T searchDate(Class<T> clazz, String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T bean = null;

        try{

            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);

            if(args!=null && args.length>0){
                for (int i=0; i<args.length; i++){
                    ps.setObject(i+1, args[i]);
                }
            }
            rs = ps.executeQuery();

            //获取结果集数据
            ResultSetMetaData metaData = rs.getMetaData();
            //获取当前结果集列数
            int columnCount = metaData.getColumnCount();
            //取出数据
            if(rs.next()){
                //key存放列名，value存放列值，for循环完成之后，rowMap存放一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();

                for(int i=1; i<=columnCount; i++){
                    //获取第i列的属性和值
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);
                    //判断查询出来的类的类型，如果是java.sql.Date转成java.util.Date
                    if (columnValue instanceof java.sql.Date){
                        java.sql.Date date = (java.sql.Date)columnValue;
                        columnValue = new Date(date.getTime());
                    }
                    //存入Map中
                    rowMap.put(columnName, columnValue);
                }

                //创建一个对象， 给对象赋值
                bean = clazz.newInstance();

                //循环rowMap给User所有属性赋值 entry {key, value}
                for (Map.Entry<String, Object> entry : rowMap.entrySet()){

                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return bean;
    }

    /**
     * @Description: 查找数据列表
     * @Param: clazz(对象类), sql（sql语句）, args(删除对象参数)
     * @return: List<T>（对象列表）
     * @Author: Yuan
     * @Date: 2020/12/27
     */
    public static  <T> List<T> searchDates(Class<T> clazz, String sql, Object...args){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = null;

        try{

            conn = DButils.getConnection();
            ps = conn.prepareStatement(sql);

            if(args!=null && args.length>0){
                for (int i=0; i<args.length; i++){
                    ps.setObject(i+1, args[i]);
                }
            }
            rs = ps.executeQuery();

            //获取结果集数据
            ResultSetMetaData metaData = rs.getMetaData();
            //获取当前结果集列数
            int columnCount = metaData.getColumnCount();
            //list初始化
            list = new ArrayList<T>();
            //取出数据
            while (rs.next()){
                //key存放列名，value存放列值，for循环完成之后，rowMap存放一条记录
                Map<String, Object> rowMap = new HashMap<String, Object>();

                for(int i=1; i<=columnCount; i++){
                    //获取第i列的属性和值
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);
                    //判断查询出来的类的类型，如果是java.sql.Date转成java.util.Date
                    if (columnValue instanceof java.sql.Date){
                        java.sql.Date date = (java.sql.Date)columnValue;
                        columnValue = new Date(date.getTime());
                    }
                    //存入Map中
                    rowMap.put(columnName, columnValue);
                }

                //创建一个对象， 给对象赋值
                T bean = clazz.newInstance();

                //循环rowMap给User所有属性赋值 entry {key, value}
                for (Map.Entry<String, Object> entry : rowMap.entrySet()){

                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();
                    BeanUtils.setProperty(bean, propertyName, propertyValue);

                }
                //list添加数据
                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return list;
    }

    /**
     * @Description: 关闭数据库
     * @Param: [conn, ps, rs]
     * @return: void
     * @Author: Yuan
     * @Date: 2020/12/28
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {

        if (rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (ps!=null){
            try {
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
