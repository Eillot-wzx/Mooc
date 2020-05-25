package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Utils {

    /**
     * C3P0Utils工具类
     * 使用默认的配置信息连接数据库
     * 配置信息存储在c3p0-config.xml中
     */

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public static DataSource getDataSourse() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            return null;
        }
    }
}
