package com.xuyao.test.other.orm;

import java.util.Map;

/**
 * 每个mapper文件解析出的信息
 */
public class MapperInfo {

    /**
     * 由dao文件生成的代理类
     */
    private Object mapperProxy;

    /**
     * 每段sql语句解析出的信息对象，key为id
     */
    private Map<String, StatementInfo> statementInfoMap;

    public Object getMapperProxy() {
        return mapperProxy;
    }

    public void setMapperProxy(Object mapperProxy) {
        this.mapperProxy = mapperProxy;
    }

    public Map<String, StatementInfo> getStatementInfoMap() {
        return statementInfoMap;
    }

    public void setStatementInfoMap(Map<String, StatementInfo> statementInfoMap) {
        this.statementInfoMap = statementInfoMap;
    }

    @Override
    public String toString() {
        return "MapperInfo{" +
                "mapperProxy=" + mapperProxy +
                ", statementInfoMap=" + statementInfoMap +
                '}';
    }
}
