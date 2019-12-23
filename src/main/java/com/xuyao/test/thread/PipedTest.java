package com.xuyao.test.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class PipedTest {
    public static void main(String[] args) throws IOException {

        //读写需要不同线程处理
        final PipedOutputStream output = new PipedOutputStream();
        final PipedInputStream  input  = new PipedInputStream(output);
        Thread thread1 = new Thread(() -> {
            try {
                output.write("Hello world, pipe!".getBytes());
            } catch (IOException e) {
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                int data = input.read();
                while(data != -1){
                    System.out.print((char) data);
                    data = input.read();
                }
            } catch (IOException e) {
            }
        });
        thread1.start();
        thread2.start();

    }

}
