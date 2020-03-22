package com.qy105.aaa.service;

import com.qy105.aaa.base.BaseService;
import com.qy105.aaa.mapper.OmsOrderMapper;
import com.qy105.aaa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    @Autowired
    private CouponService couponService;
    @Autowired
    private OmsOrderItemService omsOrderItemService;
    @Override
    public Mapper<OmsOrder> getMapper() {
        return omsOrderMapper;
    }
    /**
     * create by: ws
     * description: TODO
     * create time: 0:34 2020/3/22
     * * @Param: null
     * @return 
     */
    public Boolean createOrder(OmsCartItem omsCartItem, Object addressId, String time, int couponId){
        Mapper<OmsCartItem> mapper = omsCartItemService.getMapper();
        OmsCartItem omsCartItem1 = mapper.selectByPrimaryKey(omsCartItem.getId());
        Date createDate = omsCartItem1.getCreateDate();
        Calendar c = Calendar.getInstance();
        c.setTime(createDate);
        c.add(Calendar.MINUTE,15);
        //比较创建购物车的时间加上15分钟跟当前时间
        int i = new Date().compareTo(c.getTime());
        //定义个boolean变量  如果等于true则说明可以提交订单
        Boolean boo = false;
        //创建一个订单对象
        OmsOrder omsOrder = new OmsOrder();
        //如果当前时间大于添加购物车时间加15分钟   这是已经不占有库存了   需要重新判断库存
        if(i>=0){
            Long productId = omsCartItem1.getProductId();
            //判断是外卖商品还是堂食商品
            if(productService.getMapper().selectByPrimaryKey(productId).getRoductCategory()==1){
                //如果不是外卖订单则需要判断库存
                synchronized (OrderService.class){
                    PmsProduct pmsProduct = productService.getMapper().selectByPrimaryKey(productId);
                    Integer stock = pmsProduct.getStock();
                    if (stock>0){
                        pmsProduct.setStock(stock-omsCartItem1.getQuantity());
                        productService.getMapper().updateByPrimaryKey(pmsProduct);
                        boo=true;
                    }
                }

                if(!"null".equals(time)){
                    omsOrder.setOrderType(2);
                }else{
                    omsOrder.setOrderType(1);
                }
            }else{
                boo=true;
                omsOrder.setOrderType(0);
            }
            if (boo){
                omsOrder.setMemberId(omsCartItem1.getMemberId());
                omsOrder.setShopId(omsCartItem1.getShopId());
                SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE3);
                String format = sdf.format(new Date());
                omsOrder.setOrderSn(format);
                /*omsOrder.setCreateTime(new Date());*/
                omsOrder.setMemberUsername(memberService.getMapper().selectByPrimaryKey(omsCartItem1.getMemberId()).getUsername());
                //应付金额
                double totalAmount = omsCartItem1.getPrice() * omsCartItem1.getQuantity();
                System.out.println(omsCartItem1);
                System.out.println("单价"+omsCartItem1.getPrice());
                System.out.println("数量"+omsCartItem1.getQuantity());
                System.out.println("应付金额"+totalAmount);
                omsOrder.setTotalAmount(totalAmount);
                Coupon coupon = couponService.getMapper().selectByPrimaryKey(couponId);
                double payAmount =0;
                if(totalAmount>coupon.getMinPoint()){
                    payAmount = totalAmount-coupon.getAmount();
                    System.out.println("实付金额"+payAmount);
                }
                omsOrder.setPayAmount(payAmount);
                int insert = getMapper().insert(omsOrder);
                if (insert>0){
                    //添加订单所包含的商品表数据
                    OmsOrderItem omsOrderItem = new OmsOrderItem();
                    Long idByOrderSn = omsOrderMapper.getIdByOrderSn(format);
                    System.out.println("format"+idByOrderSn);
                    omsOrderItem.setOrderId(idByOrderSn);
                    omsOrderItem.setProductId(productId);
                    omsOrderItemService.getMapper().insert(omsOrderItem);
                }
                return boo;
            }



        }
        return boo;
    }
}
