package com.xuyao.test.pattern.adapter;

public class Usb implements IUsb {
    @Override
    public void forUsb() {
        System.out.println("by usb");
    }
}
