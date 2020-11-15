package com.xuyao.test.ftp;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Demo {

    private String ip = "";
    private int port = 21;
    private String username = "";
    private String password = "";
    //本地文件
    private String filePath = "";
    //远程文件
    private String targetPath = "";

    private FTPClient ftpClient;

    @Test
    public void test() throws IOException {
        ftpClient = initFTPClient();
//        uploadDirectory();
        downloadFile();
    }

    /**
     * 上传文件夹下文件
     * @throws IOException
     */
    private void uploadDirectory() throws IOException {
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
     * 下载文件夹下文件
     * @throws IOException
     */
    private void downloadDirectory() throws IOException {
        FTPFile[] ftpFiles = ftpClient.listFiles(targetPath);
        if(ftpFiles != null && ftpFiles.length > 0){
            for (FTPFile ftpFile : ftpFiles) {
                boolean result = ftpClient.retrieveFile(targetPath + ftpFile.getName(), new FileOutputStream(new File(filePath + ftpFile.getName())));
                System.out.println(String.format("文件名称：%s，下载结果：%s", ftpFile.getName(), result ? "成功" : "失败"));
            }
        }
        //关闭连接
        ftpClient.logout();
    }

    /**
     * 下载文件
     * @throws IOException
     */
    private void downloadFile() throws IOException {
        boolean result = ftpClient.retrieveFile(targetPath, new FileOutputStream(new File(filePath + targetPath.substring(targetPath.lastIndexOf("/")))));
        System.out.println(String.format("文件名称：%s，下载结果：%s", targetPath.substring(targetPath.lastIndexOf("/")), result ? "成功" : "失败"));
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
