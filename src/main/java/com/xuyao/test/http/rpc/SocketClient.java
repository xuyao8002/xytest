package com.xuyao.test.http.rpc;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket;

        ObjectOutputStream outputStream;
        System.out.println("客户端准备好了");
        while (true) {
            socket = new Socket("localhost", 8080);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            Person person = new Person();
            person.setName("xuyao 89");
            outputStream.writeObject(person);

            ObjectInputStream dataInputStream = new ObjectInputStream(socket.getInputStream());
            String result = (String) dataInputStream.readObject();

            System.out.println("接收：" + result + " from " + socket.getInetAddress() + " / " + socket.getPort());
            if (StringUtils.isNotBlank(result)) {
                break;
            }
            outputStream.close();
            dataInputStream.close();
            socket.close();

        }

    }

}
