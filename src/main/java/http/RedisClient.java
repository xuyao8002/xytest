package http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RedisClient {

    private InputStream inputStream;

    private OutputStream outputStream;

    private static final String HOST = "";

    private static final int PORT = 6379;

    private static final String PASSWORD = "";


    public RedisClient(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void set(String key, String value) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*3").append("\r\n");
        builder.append("$3").append("\r\n");
        builder.append("SET").append("\r\n");
        builder.append("$").append(key.getBytes().length).append("\r\n");
        builder.append(key).append("\r\n");
        builder.append("$").append(value.getBytes().length).append("\r\n");
        builder.append(value).append("\r\n");

        byte[] response = response(builder.toString().getBytes());
        System.out.println("set响应：" + new String(response));

    }

    public void get(String key) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*2").append("\r\n");
        builder.append("$3").append("\r\n");
        builder.append("GET").append("\r\n");
        builder.append("$").append(key.getBytes().length).append("\r\n");
        builder.append(key).append("\r\n");

        byte[] response = response(builder.toString().getBytes());
        System.out.println("get响应：" + new String(response));

    }

    public void auth(String password) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*2").append("\r\n");
        builder.append("$4").append("\r\n");
        builder.append("AUTH").append("\r\n");
        builder.append("$").append(password.getBytes().length).append("\r\n");
        builder.append(password).append("\r\n");

        byte[] response = response(builder.toString().getBytes());
        System.out.println("auth响应：" + new String(response));
    }

    private byte[] response(byte[] command) throws IOException {
        outputStream.write(command);
        byte[] response = new byte[1024];
        inputStream.read(response);
        return response;
    }

    public static void main(String[] args) throws IOException {
        RedisClient client = new RedisClient(HOST, PORT);
        client.auth(PASSWORD);
//        client.set("xu", "yao");
        client.get("xu");
    }
}
