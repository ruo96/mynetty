package com.wrh.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:56 2018/7/25 0025
 * @Modified By:
 */
@Slf4j
public class DBUtil {

    //连接池对象
    private static BasicDataSource ds;
    private DBUtil(){

    }
    //加载参数
    static{
        Properties p = new Properties();
        try {
            p.load(DBUtil.class.getClassLoader().getResourceAsStream("database.properties"));
            String driver = p.getProperty("jdbc.driver");
            String url = p.getProperty("jdbc.url");
            String user = p.getProperty("jdbc.username");
            String pwd = p.getProperty("jdbc.password");
            String initSize = p.getProperty("initSize");
            String maxSize = p.getProperty("maxSize");

            log.info("driver:{}",driver);
            log.info("url:{}",url);
            log.info("user:{}",user);
            log.info("pwd:{}",pwd);
            log.info("initSize:{}",initSize);
            log.info("maxSize:{}",maxSize);

            //创建连接池
            ds = new BasicDataSource();
            //设置参数
            ds.setDriverClassName(driver);
            ds.setUrl(url);
            ds.setUsername(user);
            ds.setPassword(pwd);
            ds.setInitialSize(new Integer(initSize));
            ds.setMaxActive(new Integer(maxSize));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载配置文件失败",e);
        }
    }
    /*
     * 以上就是将配置文件里的参数全部读取出来，接下来就是要
     * 写两个方法，一个是用来创建连接的，一个关闭连接
     * */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭连接失败",e);
            }
        }
    }

}
