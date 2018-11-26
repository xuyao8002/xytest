package com.example.demo;

import java.sql.*;

public class SqliteTest {
    private static String className = "org.sqlite.JDBC";
    private static String dbName = "xuyao.db";
    public static void main(String[] args) throws Exception {

        Class.forName(className);

        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

        Statement stmt = connection.createStatement();

        String sql = null;
//        sql = "CREATE TABLE COMPANY " +
//                "(ID INT PRIMARY KEY     NOT NULL," +
//                " NAME           TEXT    NOT NULL, " +
//                " AGE            INT     NOT NULL, " +
//                " ADDRESS        CHAR(50), " +
//                " SALARY         REAL)";
//        stmt.executeUpdate(sql);

//        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//                "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
//        stmt.executeUpdate(sql);


        query(stmt);

        sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
        stmt.executeUpdate(sql);
        query(stmt);

        sql = "DELETE from COMPANY where ID=2;";
        stmt.executeUpdate(sql);

        query(stmt);
    }

    public static void query(Statement stmt){
        String sql = "SELECT * FROM COMPANY;";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.print("ID = " + id );
                System.out.print(" NAME = " + name );
                System.out.print(" AGE = " + age );
                System.out.print(" ADDRESS = " + address );
                System.out.print(" SALARY = " + salary );
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
