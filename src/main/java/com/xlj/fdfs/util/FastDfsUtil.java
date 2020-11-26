package com.xlj.fdfs.util;

import org.csource.fastdfs.*;

/**
 * @author xlj
 * @date 2020/11/25 20:59
 */
public class FastDfsUtil {

    /**
     * @description 文件上传
     * @author xlj
     * @date 2020/11/25 21:03
     */
    public static String[] upload(byte[] fileBuff, String fileExtName) {
        try {
            // 加载FastDFS的配置文件
            ClientGlobal.initByProperties("application.properties");

            // 创建Tracker客户端和服务端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getTrackerServer();

            // 创建Storage客户端和服务端
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            // 执行上传
            String[] result = client.upload_file(fileBuff, fileExtName, null);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @description 文件下载
     * @author xlj
     * @date 2020/11/26 20:59
     */
    public static byte[] download(String group, String filePath) {
        try {
            // 读取文件配置
            ClientGlobal.initByProperties("application.properties");

            // 创建Tracker客户端和服务端
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();

            // 创建Storage客户端和服务端
            StorageServer storageServer = null;
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);

            // 从FastDFS服务器上下载文件
            byte[] bytes = storageClient1.download_file(group, filePath);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description 文件删除
     * @author xlj
     * @date 2020/11/26 20:59
     */
    public static void delete(String group, String filePath) {
        try {
            // 读取文件配置
            ClientGlobal.initByProperties("application.properties");

            // 创建Tracker客户端和服务端
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();

            // 创建Storage客户端和服务端
            StorageServer storageServer = null;
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);

            // 从FastDFS服务器上删除文件
            storageClient1.delete_file(group, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
