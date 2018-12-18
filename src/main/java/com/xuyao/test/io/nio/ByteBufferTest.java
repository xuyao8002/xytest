package com.xuyao.test.io.nio;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteBufferTest {

    private static int capacity = 10;

    public static void main(String[] args) {

        //分配在JVM内存
        ByteBuffer buffer = ByteBuffer.allocate(capacity);

        //直接分配在系统内存
        buffer = ByteBuffer.allocateDirect(capacity);

        byte[] array = new byte[capacity];

        System.out.println(Arrays.toString(array));

        //包装数组，与数组数据会相互影响
        buffer = ByteBuffer.wrap(array);

        buffer.put("a".getBytes());

        System.out.println(Arrays.toString(array));
        //指定位置包装数组
        buffer = ByteBuffer.wrap(array, 5, 5);

        array[5] =  'A';

        System.out.println(Arrays.toString(buffer.array()));
    }

}
