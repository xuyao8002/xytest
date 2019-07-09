package com.xuyao.test.pattern.adapter;

public class Test {
    public static void main(String[] args) {
        EthernetWrapper ie = new EthernetWrapper();
        ie.setIUsb(new Usb());
        Computer computer = new Computer();
        computer.setEthernet(ie);
        computer.networking();
    }
}
