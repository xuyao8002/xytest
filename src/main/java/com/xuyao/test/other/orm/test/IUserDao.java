package com.xuyao.test.other.orm.test;

import java.util.List;

public interface IUserDao {

    List<User> getList(User user);

    int update(User user);

    int insert(User user);

    int delete(User user);
}
