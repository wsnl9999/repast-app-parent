package com.qy105.aaa.mapper;

import com.qy105.aaa.model.OmsOrder;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface OmsOrderMapper extends Mapper<OmsOrder> {
    @Select("select id from oms_order where order_sn = #{orderSn}")
    Long getIdByOrderSn(String orderSn);
}