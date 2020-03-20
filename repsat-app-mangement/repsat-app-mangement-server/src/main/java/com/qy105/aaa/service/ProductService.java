package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.PmsProductMapper;
import com.qy105.aaa.model.PmsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/21 0:24
 * @description：
 * @modified By：
 */
@Service
public class ProductService extends BaseService<PmsProduct> {
    @Autowired
    private PmsProductMapper pmsProductMapper;
    @Override
    public Mapper<PmsProduct> getMapper() {
        return pmsProductMapper;
    }
}
