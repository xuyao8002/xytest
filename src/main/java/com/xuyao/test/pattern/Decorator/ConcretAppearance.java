package com.xuyao.test.pattern.Decorator;

/**
 * Created by xuyao on 2018/10/6.
 */
public class ConcretAppearance implements Appearance {
    @Override
    public void seems() {
        System.out.println(" ordinary ");
    }
}
