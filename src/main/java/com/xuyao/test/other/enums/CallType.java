package com.xuyao.test.other.enums;

public enum CallType {
    YOU("你", 1),
    ME("我", 2),
    HE("他", 3);

    private String name;

    private int type;

    CallType(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public static boolean contains(int type){
        CallType[] values = values();
        for (CallType value : values) {
            if(value.type == type){
                return true;
            }
        }
        return false;
    }
}
