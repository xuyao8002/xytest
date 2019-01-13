package com.xuyao.test.serialization.ProtocolBuffer;

import com.google.protobuf.ByteString;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ProtocolBuffer {

    private static int PORT = 8080;

    public static void main(String[] args) throws InterruptedException {

        Thread server = new Thread(() -> {
            try {
                ServerSocket ss = new ServerSocket(PORT);
                Socket socket = ss.accept();
                PersonBean.Person person = PersonBean.Person.parseFrom(ByteString.readFrom(socket.getInputStream()));
                if(person != null) {
                    System.out.println(Thread.currentThread().getName() + " server receive\n" + person.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        Thread client = new Thread(() -> {
            Socket socket;
            try {
                socket = new Socket("localhost", PORT);
                PersonBean.Person person = PersonBean.Person.newBuilder().setName("xy").setAge(66).setHobby("playing").build();
                OutputStream os = socket.getOutputStream();
                os.write(person.toByteArray());
                System.out.println(Thread.currentThread().getName() + " client send\n" + person.toString());
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        server.start();
        TimeUnit.SECONDS.sleep(1);
        client.start();
    }
}
