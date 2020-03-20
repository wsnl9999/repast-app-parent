package com.qy105.aaa.mapper;

import com.qy105.aaa.model.PmsProduct;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface PmsProductMapper extends Mapper<PmsProduct> {

    @Select("SELECT stock FROM pms_product where id = #{id}")
    public int getStock(int id);
}