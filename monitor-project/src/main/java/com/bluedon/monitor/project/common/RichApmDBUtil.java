package com.bluedon.monitor.project.common;


import com.bluedon.monitor.project.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by JiangFeng
 * on 2017/11/9.
 */
public class RichApmDBUtil {
    public static Connection getConnection(){
        Connection conn = null;
        String driver= PropertiesUtil.getValue("richapm.driverClassName");
        String url = PropertiesUtil.getValue("richapm.url");
        String user = PropertiesUtil.getValue("richapm.username");
        String password = PropertiesUtil.getValue("richapm.password");
        try {
            Class.forName(driver);// 加载数据库驱动程序
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url, user, password);// 获得Connection对象
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean isValid(Connection conn){
        boolean flag =false;
        if (conn != null) {
            try {
                flag = conn.isValid(5);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //如果连接失效关闭连接
            if(!flag){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return  flag;
    }
    public static void close(Connection conn){
        if (conn != null ){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection createIfNon(Connection conn){
        if (conn == null){
            conn=getConnection();
        }else {
            if(!isValid(conn)){
                conn=getConnection();
            }
        }
        return  conn;
    }
}
