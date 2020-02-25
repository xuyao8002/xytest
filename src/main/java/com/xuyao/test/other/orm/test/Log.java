package com.xuyao.test.other.orm.test;

public class Log {

    private Long id;

    private String code;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", type=" + type +
                '}';
    }
}
