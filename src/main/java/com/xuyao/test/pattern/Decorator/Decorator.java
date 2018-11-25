package com.xuyao.test.pattern.Decorator;

/**
 * Created by xuyao on 2018/10/6.
 */
public class Decorator implements Appearance {

    private Appearance appearance;

    public Decorator(Appearance appearance) {
        this.appearance = appearance;
    }

    @Override
    public void seems() {
        this.appearance.seems();
    }
}
