package com.bluedon.monitor.project.common;

import com.bluedon.monitor.project.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库工具类(获取连接、释放资源)
 *
 * @author zhouming
 *
 */
public abstract class MyDBUtil {

    // 只需要执行一次
    static{
        try {
            System.out.println("注册数据库驱动......");
            String driver= PropertiesUtil.getValue("system.driverClassName");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("注册数据库驱动出现异常:"+e.getMessage());
        }
    }
    /**
     * 获取一个连接对象
     * @return
     */
    public static Connection getConn() {
        String url = PropertiesUtil.getValue("system.url");
        String user = PropertiesUtil.getValue("system.username");
        String password = PropertiesUtil.getValue("system.password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex1) {
            throw new RuntimeException("无法获取连接,原因:"+ex1.getMessage());
        }
    }


    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (null != rs) {
                rs.close();
                System.out.println("ResultSet已关闭！");
            }
        } catch (SQLException e) {
            throw new RuntimeException("关闭ResultSet出现异常:" + e.getMessage());
        } finally {
            try {
                if (null != st) {
                    st.close();
                    System.out.println("Statement已关闭！");
                }
            } catch (SQLException e) {
                throw new RuntimeException("关闭Statement出现异常:" + e.getMessage());
            } finally {
                if (null != conn) {
                    try {
                        conn.close();
                        System.out.println("Connection已关闭！");
                    } catch (SQLException e) {
                        throw new RuntimeException("关闭Connection出现异常:"
                                + e.getMessage());
                    }
                }
            }
        }

    }
}