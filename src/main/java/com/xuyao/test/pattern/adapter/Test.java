package com.xuyao.test.pattern.adapter;

/**
 * Created by xuyao on 2018/10/5.
 */
public class Test {
    public static void main(String[] args) {
        Usb usb = new Usb();
        UsbToIEthernet ie = new UsbToIEthernet();
        ie.setIUsb(usb);
        ie.forEthernet();
    }
}
