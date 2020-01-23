package com.xuyao.test.other.orm.test;

import java.util.List;

public interface ILogDao {

    List<Log> getList(Log log);

    Log get(Log log);

}
