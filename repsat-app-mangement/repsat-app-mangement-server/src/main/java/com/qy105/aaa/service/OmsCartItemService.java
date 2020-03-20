package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.OmsCartItemMapper;
import com.qy105.aaa.model.OmsCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/21 0:06
 * @description：
 * @modified By：
 */
@Service
public class OmsCartItemService extends BaseService<OmsCartItem> {
    @Autowired
    private OmsCartItemMapper omsCartItemMapper;
    @Override
    public Mapper<OmsCartItem> getMapper() {
        return omsCartItemMapper;
    }
}
