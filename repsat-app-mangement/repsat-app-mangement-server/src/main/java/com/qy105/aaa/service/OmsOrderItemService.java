package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.OmsOrderItemMapper;
import com.qy105.aaa.mapper.OmsOrderMapper;
import com.qy105.aaa.model.OmsOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/22 23:34
 * @description：
 * @modified By：
 */
@Service
public class OmsOrderItemService extends BaseService<OmsOrderItem> {
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;
    @Override
    public Mapper<OmsOrderItem> getMapper() {
        return omsOrderItemMapper;
    }
}
