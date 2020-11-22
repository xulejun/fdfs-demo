package com.xlj.fdfs.utils;

import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * FastDFS文件下载
 *
 * @author xlj
 * @date 2020/11/22 16:51
 */
public class DownLoad {
    public static void main(String[] args) {
        try {
            // 读取文件配置
            ClientGlobal.initByProperties("application.properties");

            // 创建Tracker客户端和服务端
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();

            // 创建Storage客户端和服务端
            StorageServer storageServer = null;
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);

            // 从FastDFS服务器上下载文件
            byte[] bytes = storageClient1.download_file1("group1/M00/00/00/wKgrN1-6IJuACEsDAAIpQEf1Wt0176.jpg");
            File file = new File("C:\\Users\\Administrator\\Desktop\\fastdfs.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            System.out.println("下载完成！");
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
