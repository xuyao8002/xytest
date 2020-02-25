package com.xuyao.test.http.rpc.transmission;

/**
 * 处理null值
 */
public enum SpecialValue {

    NULL("null", "null");

    private String value;
    private String desc;

    SpecialValue(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
