package com.xuyao.test.http.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket;

        DataOutputStream outputStream;
        BufferedReader bufferedReader;
        System.out.println("客户端准备好了");
        socket = new Socket("localhost", 8080);
        new Thread(new ReadThread(socket)).start();
        new Thread(new WriteThread(socket)).start();
//        boolean exitFlag = false;
//        while (true) {
//            socket = new Socket("localhost", 8080);
//            outputStream = new DataOutputStream(socket.getOutputStream());
//
//
//            System.out.print("请输入：");
//            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//            outputStream.writeUTF(bufferedReader.readLine());
//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//            String readUTF = dataInputStream.readUTF();
//            System.out.println("接收：" + readUTF + " from " + socket.getInetAddress() + " / " + socket.getPort());
//            if("bye".equals(readUTF)){
//                exitFlag = true;
//                System.out.println("我退出了");
//            }
//            outputStream.close();
//            dataInputStream.close();
//            socket.close();
//            if(exitFlag) break;
//        }

    }

    static class WriteThread implements Runnable{
        private Socket socket;
        private DataOutputStream outputStream;
        private BufferedReader bufferedReader;
        public WriteThread(Socket socket) throws IOException {
            this.socket = socket;
            outputStream = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }
        @Override
        public void run() {
            while(true){
                try {
                    outputStream.writeUTF(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ReadThread implements Runnable{

        private Socket socket;
        private DataInputStream dataInputStream;
        public ReadThread(Socket socket) throws IOException {
            this.socket = socket;
            dataInputStream = new DataInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while(true){
                try {
                    String readUTF = dataInputStream.readUTF();
                    System.out.println("接收：" + readUTF + " from " + socket.getInetAddress() + " / " + socket.getPort());
                    if("bye".equals(readUTF)){
                        System.out.println("我退出了");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
