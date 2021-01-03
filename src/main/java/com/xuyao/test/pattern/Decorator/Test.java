package com.xuyao.test.pattern.Decorator;

public class Test {

    public static void main(String[] args) {
        Draw draw = new Draw();
        draw.doSomething();
        System.out.println();
        Paint paint = new Paint(draw);
        paint.doSomething();
    }
}
