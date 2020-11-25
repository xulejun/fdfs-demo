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
    public static String[] upload(byte[] file_buff, String fileExtName) {
        try {
            // 加载FastDFS的配置文件
            ClientGlobal.initByProperties("application.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            // 创建Tracker客户端和服务端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getTrackerServer();

            // 创建Storage客户端和服务端
            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            // 执行上传
            String[] result = client.upload_file(file_buff, fileExtName, null);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
