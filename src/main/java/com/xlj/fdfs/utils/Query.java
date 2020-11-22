package com.xlj.fdfs.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;


/**
 * FastDFS文件查询
 *
 * @author xlj
 * @date 2020/11/22 16:30
 */
public class Query {
    public static void main(String[] args) {
        try {
            ClientGlobal.initByProperties("application.properties");
            // 创建Tracker客户端和服务端
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();

            // 创建Storage客户端和服务端
            StorageServer storageServer = null;
            StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);

            // 执行查询
            FileInfo fileInfo = storageClient.query_file_info("group1", "M00/00/00/wKgrN1-6IJuACEsDAAIpQEf1Wt0176.jpg");
            FileInfo fileInfo1 = storageClient.query_file_info1("group1/M00/00/00/wKgrN1-6IJuACEsDAAIpQEf1Wt0176.jpg");
            System.out.println(fileInfo);
            System.out.println(fileInfo1);
            // 查询元信息
            NameValuePair[] metadata1 = storageClient.get_metadata1("group1/M00/00/00/wKgrN1-6IJuACEsDAAIpQEf1Wt0176.jpg");
            System.out.println(metadata1[0].getName() + "=" + metadata1[0].getValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
