package com.xuyao.test.other.orm;

import java.util.List;

public class MapperInfo {

    private String namespace;

    private List<StatementInfo> statementInfos;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<StatementInfo> getStatementInfos() {
        return statementInfos;
    }

    public void setStatementInfos(List<StatementInfo> statementInfos) {
        this.statementInfos = statementInfos;
    }
}
