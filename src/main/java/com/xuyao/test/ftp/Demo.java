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
    private String targetName = "";


    @Test
    public void test() throws IOException {
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
        //读取本地文件
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        //设置上传的路径
        ftpClient.changeWorkingDirectory(targetPath);
        //修改上传文件的格式为二进制
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //服务器存储文件
        ftpClient.storeFile(targetName, inputStream);
        //关闭连接
        ftpClient.logout();
    }
}
