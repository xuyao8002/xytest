package com.xuyao.test.pattern.adapter;

public class Computer {

    private IEthernet ethernet;

    public void setEthernet(IEthernet ethernet) {
        this.ethernet = ethernet;
    }

    public void networking(){
        System.out.println("开始连网");
        ethernet.forEthernet();
        System.out.println("连上了");
    }
}
