package com.xlj.fdfs.service.impl;

import com.xlj.fdfs.mapper.BorrowerMapper;
import com.xlj.fdfs.po.BorrowerPO;
import com.xlj.fdfs.service.BorrowerService;
import com.xlj.fdfs.util.FastDfsUtil;
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
    public List<BorrowerPO> selectAll() {
        return borrowerMapper.selectAll();
    }

    @Override
    public BorrowerPO selectById(Integer id) {
        return borrowerMapper.selectById(id);
    }

    @Override
    public void update(BorrowerPO borrowerPo) {
        borrowerMapper.update(borrowerPo);
    }

    @Override
    public void deleteById(Integer id) {
        BorrowerPO borrowerPo = borrowerMapper.selectById(id);
        FastDfsUtil.delete(borrowerPo.getGroupName(), borrowerPo.getFilePath());
        borrowerPo.setGroupName("")
                .setFilePath("")
                .setFileSize(null)
                .setFileName("");
        borrowerMapper.update(borrowerPo);
    }
}
