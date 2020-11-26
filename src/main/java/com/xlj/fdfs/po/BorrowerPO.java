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
public class BorrowerPO {
    /**
     * 自增长序列
     */
    private Integer id;

    /**
     * 借款人姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 借款人电话
     */
    private String phone;

    /**
     * 借款金额
     */
    private Double money;

    /**
     * 文件组名
     */
    private String groupName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public BorrowerPO forInsert(){
        this.createTime = this.updateTime = String.valueOf(System.currentTimeMillis());
        return this;
    }


}
