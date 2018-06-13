package com.sun.demo.common;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Wayne on 2017/8/2.
 */
public class FtpUtils {

    private FTPClient ftp = null;

    public boolean uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) throws Exception {
        boolean success = false;
        try {
            int reply;
            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);

            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public boolean uploadFile(String path, String filename, InputStream input) throws Exception {
        boolean success = false;
        try {
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean connect(String path, String addr, int port, String username, String password) {
        try {
            //FTPClient ftp = new FTPHTTPClient(addr, port, username, password);
            ftp = new FTPClient();
            int reply;
            ftp.connect(addr);
            System.out.println("连接到：" + addr + ":" + port);
            System.out.print(ftp.getReplyString());
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.err.println("FTP目标服务器积极拒绝.");
                System.exit(1);
                return false;
            }else{
                ftp.login(username, password);
                ftp.enterLocalPassiveMode();
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftp.changeWorkingDirectory(path);
                System.out.println("已连接：" + addr + ":" + port);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void createDir(String dirname){
        try{
            ftp.makeDirectory(dirname);
            System.out.println("在目标服务器上成功建立了文件夹: " + dirname);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void disconnect(){
        try {
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWorkingDir(String path){
        try {
            ftp.changeWorkingDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
