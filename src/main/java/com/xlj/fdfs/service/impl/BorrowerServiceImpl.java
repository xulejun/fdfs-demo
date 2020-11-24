package com.xlj.fdfs.service.impl;

import com.xlj.fdfs.mapper.BorrowerMapper;
import com.xlj.fdfs.po.BorrowerPo;
import com.xlj.fdfs.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xlj
 * @date 2020/11/24 21:37
 */
@Service
public class BorrowerServiceImpl implements BorrowerService {
    @Resource
    private BorrowerMapper borrowerMapper;

    @Override
    public List<BorrowerPo> selectAll() {
        return borrowerMapper.selectAll();
    }

    @Override
    public BorrowerPo selectById(Integer id) {
        return borrowerMapper.selectById(id);
    }
}
