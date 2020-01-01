package com.xuyao.test.http.rpc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private static IGreetService greetService = new GreatServiceImpl();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务端准备好了");
        while(true){
            Socket accept = serverSocket.accept();
            new Thread(new Handler(accept)).start();
        }

    }

    static class Handler implements Runnable{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
                Person person = (Person) inputStream.readObject();
                System.out.println("接收：" + person + " from " + socket.getInetAddress() + " / " + socket.getPort());

                String greeting = greetService.greeting(person);
                ObjectOutputStream dataOutputStream = new ObjectOutputStream(socket.getOutputStream());
                dataOutputStream.writeObject(greeting);

                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}
