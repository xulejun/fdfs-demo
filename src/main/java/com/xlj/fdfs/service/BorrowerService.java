package com.xlj.fdfs.service;

import com.xlj.fdfs.po.BorrowerPo;

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
    List<BorrowerPo> selectAll();

    /**
     * 根据id查询借款人信息
     *
     * @param id
     * @return BorrowerPo
     */
    BorrowerPo selectById(Integer id);
}
