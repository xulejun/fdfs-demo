package com.xlj.fdfs.service;

import com.xlj.fdfs.po.BorrowerPO;

import java.util.List;

/**
 * @author xlj
 * @date 2020/11/24 21:35
 */
public interface BorrowerService {
    /**
     * 查询所有的借款人信息
     * @return List<BorrowerPo>
     */
    List<BorrowerPO> selectAll();

    /**
     * 根据id查询借款人信息
     *
     * @param id
     * @return BorrowerPo
     */
    BorrowerPO selectById(Integer id);

    /**
     * 更新借款人信息
     * @param borrowerPo
     */
    void update(BorrowerPO borrowerPo);

    /**
     * 根据id删除文件及信息
     * @param id
     */
    void deleteById(Integer id);
}
