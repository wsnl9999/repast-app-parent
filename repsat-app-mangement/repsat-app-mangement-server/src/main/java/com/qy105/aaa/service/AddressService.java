package com.qy105.aaa.service;


import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.AddressMapper;
import com.qy105.aaa.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;


@Service
public class AddressService extends BaseService<Address> {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Mapper<Address> getMapper() {
        return addressMapper;
    }
    /*
     * @Author Xie
     * @Description 
     *       修改默认地址
     * @Date 17:25 2020/3/15
     * @Param [address]
     * @return java.lang.Boolean
     **/
//
    public Boolean updateAddressStatus(Address address)throws Exception{

      address.setDefaultStatus(0);
        Address address1 =new Address();
        address1.setMemberId(address.getMemberId());
        address1.setId(null);

//        Integer update1 = getBaseService().update(address1);
        List<Address> addresses = queryList(address1);
        List list = new ArrayList();
        for(Address a:addresses){
            list.add(a.getId());
        }
        address1.setDefaultStatus(1);
        Integer integer = updateBatch(address1, list, null);
        if(null!=integer&&integer>=0){
            Integer update = update(address);
            if(null!=update&&update>0){
                return true;
            }else {
                throw new Exception();
            }
        }
        return false;

    }

}
