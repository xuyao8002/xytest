package com.xuyao.test.pattern.Decorator;

public class Paint implements Action{

    private Action action;

    public Paint(Action action) {
        this.action = action;
    }

    @Override
    public void doSomething() {
        action.doSomething();
        System.out.print(", 涂颜色");
    }
}
