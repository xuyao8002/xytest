package com.xuyao.test.pattern.adapter;

/**
 * Created by xuyao on 2018/10/5.
 */
public class UsbToIEthernet implements IEthernet {

    private IUsb IUsb;

    public void setIUsb(IUsb IUsb) {
        this.IUsb = IUsb;
    }

    @Override
    public void forEthernet() {
        IUsb.forUsb();
    }
}
