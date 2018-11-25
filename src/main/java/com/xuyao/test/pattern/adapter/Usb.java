package com.xuyao.test.pattern.adapter;

/**
 * Created by xuyao on 2018/10/5.
 */
public class Usb implements IUsb {
    @Override
    public void forUsb() {
        System.out.println("is usb");
    }
}
