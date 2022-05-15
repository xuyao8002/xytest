package com.xuyao.test.http.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务端准备好了");
        while(true){
            Socket accept = serverSocket.accept();
//            new Thread(new Handler(accept)).start();
            new Thread(new Read(accept)).start();
            new Thread(new Write(accept)).start();
        }

    }

    static class Read implements Runnable{
        private Socket socket;
        private DataInputStream inputStream;
        private DataOutputStream dataOutputStream;

        public Read(Socket socket) throws IOException {
            this.socket = socket;
            inputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        }

        @Override
        public void run() {
            while(true){
                try {
                    System.out.println("接收：" + inputStream.readUTF() + " from " + socket.getInetAddress() + " / " + socket.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Write implements Runnable{
        private Socket socket;
        private DataInputStream inputStream;
        private DataOutputStream dataOutputStream;
        private BufferedReader bufferedReader;
        public Write(Socket socket) throws IOException {
            this.socket = socket;
            inputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        @Override
        public void run() {
            while(true){
                try {
                    dataOutputStream.writeUTF(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

//    static class Handler implements Runnable{
//        private Socket socket;
//
//        public Handler(Socket socket) {
//            this.socket = socket;
//        }
//
//        @Override
//        public void run() {
//            DataInputStream inputStream = null;
//            try {
//                inputStream = new DataInputStream(socket.getInputStream());
//                System.out.println("接收：" + inputStream.readUTF() + " from " + socket.getInetAddress() + " / " + socket.getPort());
//                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//                System.out.print("请输入：");
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//                dataOutputStream.writeUTF(bufferedReader.readLine());
//                inputStream.close();
//                dataOutputStream.close();
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}
