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

        array[5] =  'a';

        System.out.println(Arrays.toString(buffer.array()));

        buffer = ByteBuffer.allocate(10);
        //position:0 limit:10 capacity:10
        buffer.put("hello".getBytes());
        //position:5 limit:10 capacity:10
        System.out.println(buffer + "，" + new String(buffer.array()));
        buffer.flip();
        //position:0 limit:5 capacity:10
        System.out.println(buffer + ", " + (char)buffer.get());
        //position:1 limit:5 capacity:10
        buffer.rewind();
        //position:0 limit:5 capacity:10
        System.out.println(buffer + ", " + (char)buffer.get());

    }

}
