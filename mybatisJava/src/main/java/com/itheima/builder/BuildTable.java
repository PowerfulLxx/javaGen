package com.itheima.builder;

import com.itheima.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class BuildTable {

    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection conn = null;

    private static String SQL_SHOW_TABLE_STATUES = "show table status";
    static {
        String driverName = PropertiesUtils.getProperty("db.driver.name");
        String url = PropertiesUtils.getProperty("db.url");
        String username = PropertiesUtils.getProperty("db.username");
        String password = PropertiesUtils.getProperty("db.password");
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            logger.error("数据库连接失败",e);
        }
    }

    public static void getTables() throws SQLException {
        PreparedStatement ps = null;
        ResultSet tableResult = null;
        try {
            ps = conn.prepareStatement(SQL_SHOW_TABLE_STATUES);
            tableResult = ps.executeQuery();
            while (tableResult.next()) {
                String tableName = tableResult.getString("NAME");
                String comment = tableResult.getString("COMMENT");
                logger.info("tableName:{}, comment:{}", tableName, comment);
            }
        }
        catch (Exception e) {
            logger.error("读取表失败");
        }
        finally {
            if (tableResult != null) {
                tableResult.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
