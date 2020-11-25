package com.xlj.fdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

/**
 * FastDFS文件上传
 *
 * @author xlj
 * @date 2020/11/22 15:56
 * <p>
 * 在此文件通过FastDFS的Client代码访问tracker和storage
 * 通过client的api代码方便访问 tracker 和storage，socket协议
 */
public class UploadTest {
    public static void main(String[] args) {
        upload();
    }

    private static void upload() {
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

            // 文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", "1.png");

            // 执行上传
            String fileId = client.upload_file1("C:\\Users\\Administrator\\Desktop\\JYJ.jpg", "jpg", metaList);
            // group1/M00/00/00/wKgrN1-6dZaATxTmAAIpQA1oU4o642.jpg
            // group1/M00/00/00/wKgrN1-6fH2AM_L_AAO8jSbtuo0251.jpg
            System.out.println("upload success. file id is: " + fileId);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
