package com.xuyao.test.other.orm;

import org.dom4j.Node;

import java.util.List;

public class StatementInfo {

    /**
     * sql类型，insert/delete/update/select
     */
    private String type;

    /**
     * 语句id
     */
    private String id;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 返回值类型
     */
    private Class resultType;

    /**
     * sql语句
     */
    private String sql;

    /**
     * 标签内sql语句解析出的节点集合
     */
    private List<Node> sqlNodes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Class getResultType() {
        return resultType;
    }

    public void setResultType(Class resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }


    public List<Node> getSqlNodes() {
        return sqlNodes;
    }

    public void setSqlNodes(List<Node> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    @Override
    public String toString() {
        return "StatementInfo{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", paramType='" + paramType + '\'' +
                ", resultType=" + resultType +
                ", sql='" + sql + '\'' +
                ", sqlNodes=" + sqlNodes +
                '}';
    }
}
