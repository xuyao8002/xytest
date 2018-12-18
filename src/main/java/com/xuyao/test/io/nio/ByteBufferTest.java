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

        //position:0 limit:10 capacity:10
        buffer = ByteBuffer.allocate(10);

        //position:5 limit:10 capacity:10
        buffer.put("hello".getBytes());

        System.out.println(buffer + "，" + new String(buffer.array()));
        //position:0 limit:5 capacity:10
        buffer.flip();

        //position:1 limit:5 capacity:10
        System.out.println(buffer + ", " + (char)buffer.get());

        //position:0 limit:5 capacity:10
        buffer.rewind();

        System.out.println(buffer + ", " + (char)buffer.get());

        //position:0 limit:10 capacity:10
        buffer.clear();

        System.out.println(buffer);

        buffer.put("world".getBytes());
        buffer.flip();
        buffer.get(); //取出w
        buffer.get(); //取出o
        //position:3 limit:10 capacity:10
        buffer.compact(); //将未取出的rld移动到数组最前端(0-2)，position移动到下一个可写位置(3)，为接下来的写操作准备

        System.out.println(buffer);

    }

}
