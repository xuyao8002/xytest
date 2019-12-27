package com.xuyao.test.other.objectinfo;

import org.openjdk.jol.info.ClassLayout;

/**
 * 查看对象布局、大小信息
 * 在HotSpot虚拟机中，对象在内存中存储的布局可以分为三个区域：对象头(Header)、实例数据(Instance Data)、对齐填充(Padding)
 * 对象头固定12字节，Info字段4字节，String字段4字节，加起来不是8的倍数，填充4字节，一共24字节
 */
public class ObjectInfo {

    public static void main(String[] args) {
        System.out.print(ClassLayout.parseClass(Info.class).toPrintable());
    }

}
