package com.xuyao.test.sql;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static{
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/xxx?rewriteBatchedStatements=true");
        dataSource.setUser("xxx");
        dataSource.setPassword("xxx");
    }

     public static Connection getConnection() throws SQLException {
         return dataSource.getConnection();
     }
}
