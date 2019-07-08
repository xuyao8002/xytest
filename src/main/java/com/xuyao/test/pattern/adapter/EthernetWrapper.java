package com.xuyao.test.pattern.adapter;

public class EthernetWrapper implements IEthernet {

    private IUsb IUsb;

    public void setIUsb(IUsb IUsb) {
        this.IUsb = IUsb;
    }

    @Override
    public void forEthernet() {
        IUsb.forUsb();
    }
}
