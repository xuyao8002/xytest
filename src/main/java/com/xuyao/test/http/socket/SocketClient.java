package com.xuyao.test.http.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket;

        DataOutputStream outputStream;
        BufferedReader bufferedReader;
        System.out.println("客户端准备好了");
        boolean exitFlag = false;
        while (true) {
            socket = new Socket("localhost", 8080);
            outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            System.out.print("请输入：");
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            outputStream.writeUTF(bufferedReader.readLine());
            String readUTF = dataInputStream.readUTF();
            System.out.println("接收：" + readUTF + " from " + socket.getInetAddress() + " / " + socket.getPort());
            if("bye".equals(readUTF)){
                exitFlag = true;
                System.out.println("我退出了");
            }
            outputStream.close();
            dataInputStream.close();
            socket.close();
            if(exitFlag) break;
        }

    }

}
