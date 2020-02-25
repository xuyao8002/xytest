package com.xuyao.test.other.orm.test;


import com.xuyao.test.other.orm.Configuration;
import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws DocumentException, SQLException, ClassNotFoundException, FileNotFoundException {
        Configuration configuration = new Configuration();
        configuration.build("orm/orm.xml");
        configuration.loadMapperInfos("orm", "Mapper.xml");

        IUserDao userDao = configuration.getMapperProxy(IUserDao.class);
        User user = new User();
        user.setName("uname");
        List<User> list = userDao.getList(user);
        System.out.println(list);

//        user.setEmail("a@b.com");
//        int update = userDao.update(user);
//        System.out.println(update);

//        User insert = new User();
//        insert.setName("un");
//        insert.setUsername("un2020");
//        insert.setPassword("pw");
//        insert.setGender(1);
//        insert.setPhone("139");
//        int insert1 = userDao.insert(insert);
//        System.out.println("insert: " + insert1);

//        User delete = new User();
//        delete.setName("un");
//        int delete1 = userDao.delete(delete);
//        System.out.println(delete1);

        ILogDao logDao = (ILogDao) configuration.getMapperProxy(ILogDao.class);

        Log log = logDao.get(null);
        System.out.println(log);

        List<Log> list1 = logDao.getList(null);
        System.out.println(list1);

    }

}
