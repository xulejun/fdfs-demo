package com.xlj.fdfs.mapper;

import com.xlj.fdfs.po.BorrowerPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xlj
 * @date 2020/11/24 21:35
 */
@Mapper
public interface BorrowerMapper {
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
    BorrowerPo selectById(@Param(value = "id") Integer id);
}
