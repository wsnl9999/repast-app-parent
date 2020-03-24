package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.OmsOrderMapper;
import com.qy105.aaa.model.OmsCartItem;
import com.qy105.aaa.model.OmsOrder;
import com.qy105.aaa.model.PmsProduct;
import com.qy105.aaa.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.annotation.Order;
import tk.mybatis.mapper.common.Mapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static com.qy105.aaa.staticstatus.StaticCode.FORMAT_DATE2;
import static com.qy105.aaa.staticstatus.StaticCode.FORMAT_DATE3;

/**
 * @author ：小男神
 * @date ：Created in 2020/3/21 0:02
 * @description：
 * @modified By：
 */
@Service
public class OrderService extends BaseService<OmsOrder> {
    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private OmsCartItemService omsCartItemService;

    @Autowired
    private ProductService productService;
    @Autowired
    private MemberService memberService;
    @Override
    public Mapper<OmsOrder> getMapper() {
        return omsOrderMapper;
    }
    public Boolean createOrder(OmsCartItem omsCartItem){
        Mapper<OmsCartItem> mapper = omsCartItemService.getMapper();
        OmsCartItem omsCartItem1 = mapper.selectByPrimaryKey(omsCartItem.getId());
        Date createDate = omsCartItem1.getCreateDate();
        Calendar c = Calendar.getInstance();
        c.setTime(createDate);
        c.add(Calendar.MINUTE,15);
        //比较创建购物车的时间加上15分钟跟当前时间
        int i = new Date().compareTo(c.getTime());
        Boolean boo = false;
        //如果当前时间大于添加购物车时间加15分钟   这是已经不占有库存了   需要重新判断库存
        if(i>=0){
            Long productId = omsCartItem1.getProductId();

            synchronized (OrderService.class){
                PmsProduct pmsProduct = productService.getMapper().selectByPrimaryKey(productId);
                Integer stock = pmsProduct.getStock();
                if (stock>0){
                    pmsProduct.setStock(stock-omsCartItem1.getQuantity());
                    productService.getMapper().updateByPrimaryKey(pmsProduct);
                    boo=true;
                }
            }
            if (boo){
                OmsOrder omsOrder = new OmsOrder();
                omsOrder.setMemberId(omsCartItem1.getMemberId());
                omsOrder.setShopId(omsCartItem1.getShopId());
                SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE2);
                omsOrder.setOrderSn(sdf.format(new Date()));
                /*omsOrder.setCreateTime(new Date());*/
                omsOrder.setMemberUsername(memberService.getMapper().selectByPrimaryKey(omsCartItem1.getMemberId()).getUsername());
                omsOrder.setTotalAmount((omsCartItem1.getPrice()*omsCartItem1.getQuantity()));
                getMapper().insert(omsOrder);
                return boo;
            }



        }
        return boo;
    }


}
