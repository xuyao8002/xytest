package com.xuyao.test.sql;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DbUtilsTest {

    public static void main(String[] args)  {
        Connection connection = null;
        try{
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("insert into a values(?)");
            for(int i = 0; i < 1000; i++){
                ps.setString(1, i+"");
                ps.addBatch();
            }
            ps.executeBatch();
            ps.clearBatch();
            DbUtils.commitAndCloseQuietly(connection);
        }catch (Exception e){
            DbUtils.rollbackAndCloseQuietly(connection);
            e.printStackTrace();
        }


    }

}
