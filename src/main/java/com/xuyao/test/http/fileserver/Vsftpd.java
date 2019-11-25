package com.xuyao.test.http.fileserver;



import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Vsftpd {

    private String ip = "";
    private int port = 21;
    private String username = "";
    private String password = "";
    private String filePath = "";
    private String targetPath = "";
    private String targetName = "";


    @Test
    public void test() throws IOException {
        // 1. 创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        // 2. 创建 ftp 连接
        ftpClient.connect(ip, port);
        // 3. 登录 ftp 服务器
        ftpClient.login(username, password);
        // 4. 读取本地文件
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        // 5. 设置上传的路径
        ftpClient.changeWorkingDirectory(targetPath);
        // 6. 修改上传文件的格式为二进制
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // 7. 服务器存储文件，第一个参数是存储在服务器的文件名，第二个参数是文件流
        ftpClient.storeFile(targetName, inputStream);
        // 8. 关闭连接
        ftpClient.logout();
    }
}
