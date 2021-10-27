package com.wrh.classloader;

import java.sql.*;

/**
 * @author wuruohong
 * @Classname TestDBConnect
 * @Description TODO
 * @Date 2021/10/22 17:47
 */
public class TestDBConnect {

    public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://172.16.39.148:3306/data_center";
    public static final String DBUSER = "root";
    public static final String DBPASS = "1QWed$%&098pln@@_11";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName(DBDRIVER);  // 使用class类加载驱动，反射机制的i体现
        con = DriverManager.getConnection(DBURL, DBUSER, DBPASS); //链接数据库
        PreparedStatement preparedStatement = con.prepareStatement("select id from user limit 1");
        ResultSet set = preparedStatement.executeQuery();
        System.out.println("set.getRow() = " + set.getRow());
        System.out.println("0 = " + set.getString(0));
        System.out.println("1 = " + set.getString(1));
        System.out.println("2 = " + set.getString(2));
        System.out.println("3 = " + set.getString(3));
        System.out.println("4 = " + set.getString(4));
        con.close();
    }



}
