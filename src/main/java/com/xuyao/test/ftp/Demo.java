package com.xuyao.test.ftp;



import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Demo {

    private String ip = "";
    private int port = 21;
    private String username = "";
    private String password = "";
    private String filePath = "";
    private String targetPath = "";

    @Test
    public void test() throws IOException {
        uploadDirectory();
    }

    /**
     * 上传文件夹下文件
     * @throws IOException
     */
    private void uploadDirectory() throws IOException {
        FTPClient ftpClient = initFTPClient();
        //读取本地文件
        File dire = new File(filePath);
        File[] files = dire.listFiles();
        for (File file : files) {
            //服务器存储文件
            String name = file.getName();
            boolean result = ftpClient.storeFile(name, new FileInputStream(file));
            System.out.println(String.format("文件名称：%s，上传结果：%s", name, result ? "成功" : "失败"));
        }
        //关闭连接
        ftpClient.logout();
    }

    /**
     * 上传文件
     * @throws IOException
     */
    private void uploadFile() throws IOException {
        FTPClient ftpClient = initFTPClient();
        File file = new File(filePath);
        //读取本地文件
        FileInputStream inputStream = new FileInputStream(file);
        //服务器存储文件
        String targetName = file.getName();
        ftpClient.storeFile(targetName, inputStream);
        //关闭连接
        ftpClient.logout();
    }

    /**
     * 初始FTPClient对象
     * @return
     * @throws IOException
     */
    private FTPClient initFTPClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        //创建连接
        ftpClient.connect(ip, port);
        //登录服务器
        ftpClient.login(username, password);
        //设置连接超时时间
        ftpClient.setConnectTimeout(50000);
        //设置字符编码
        ftpClient.setControlEncoding("UTF-8");
        //设置被动模式，由客户端连接服务器传输数据
        ftpClient.enterLocalPassiveMode();
        //设置上传的路径
        ftpClient.changeWorkingDirectory(targetPath);
        //修改上传文件的格式为二进制
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        return ftpClient;
    }
}
