package com.xlj.fdfs.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 借款人信息
 * @author xlj
 * @date 2020/11/23 23:12
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BorrowerPo {
    private int id;

    private String name;

    private int sex;

    private String phone;

    private double money;

    private String groupName;

    private String filePath;

    private String fileName;

    private int fileSize;

    private String createTime;

    private String updateTime;

    public void forInsert(){
        this.createTime = this.updateTime = String.valueOf(System.currentTimeMillis());
    }
}
