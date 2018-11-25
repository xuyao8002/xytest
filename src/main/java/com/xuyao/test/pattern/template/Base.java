package com.xuyao.test.pattern.template;

public abstract class Base {
    public final String all(){
        long b = System.currentTimeMillis();
        String str = execute();
        long a = System.currentTimeMillis();
        System.out.println("耗时：" + (a - b));
        return str;
    }

    public abstract String execute();
}
