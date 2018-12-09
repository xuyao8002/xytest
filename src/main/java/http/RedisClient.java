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

    private static final String LF_CR = "\r\n";


    public RedisClient(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void set(String key, String value) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*3").append(LF_CR);
        builder.append("$3").append(LF_CR);
        builder.append("SET").append(LF_CR);
        builder.append("$").append(key.getBytes().length).append(LF_CR);
        builder.append(key).append(LF_CR);
        builder.append("$").append(value.getBytes().length).append(LF_CR);
        builder.append(value).append(LF_CR);

        byte[] response = response(builder.toString().getBytes());
        System.out.println("set响应：" + new String(response));

    }

    public void get(String key) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*2").append(LF_CR);
        builder.append("$3").append(LF_CR);
        builder.append("GET").append(LF_CR);
        builder.append("$").append(key.getBytes().length).append(LF_CR);
        builder.append(key).append(LF_CR);

        byte[] response = response(builder.toString().getBytes());
        System.out.println("get响应：" + new String(response));

    }

    public void auth(String password) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("*2").append(LF_CR);
        builder.append("$4").append(LF_CR);
        builder.append("AUTH").append(LF_CR);
        builder.append("$").append(password.getBytes().length).append(LF_CR);
        builder.append(password).append(LF_CR);

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
