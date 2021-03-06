package com.qy105.aaa.service;
import com.qy105.aaa.base.ResultData;
import com.qy105.aaa.config.FeignMultiPartConfig;
import com.qy105.aaa.fallback.RepastFallBackFactory;
import com.qy105.aaa.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

import static com.qy105.aaa.staticstatus.StaticCode.ID;
import static com.qy105.aaa.staticstatus.StaticCode.TOKEN;

//@FeignClient(value = "memberinfo-interface",fallbackFactory = RepastFallBackFactory.class,configuration = FeignMultiPartConfig.class)
@FeignClient(value = "memberinfo-interface")
public interface IRepastService {
    /**
     * create by: ws
     * description: TODO   登录方法
     *
     * create time: 17:41 2020/3/11
     * * @Param: null
     * @return
     */
    @RequestMapping("/doLogin")
    Boolean doLogin(@RequestBody Member member);

    @RequestMapping("/doLoginOut")
    Boolean doLoginOut(@RequestParam(TOKEN) String token);

    /**
     * create by: ws
     * description: TODO
     *          添加登录日志方法
     * create time: 16:12 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("saveLog")
    Boolean saveLog(LoginLog loginLog);
    /**
     * create by: ws
     * description: TODO 查询用户积分操作
     * create time: 17:55 2020/3/14
     * * @Param: token
     * @return
     */
    @RequestMapping("/getIntegrationByToken")
    List<Map> getIntegrationByToken(@RequestParam(TOKEN) String token);
    /**
     * create by: ws
     * description: TODO 查询用户积分规则操作
     * create time: 17:55 2020/3/14
     * * @Param: token
     * @return
     */
    @RequestMapping("/getMemberRuleSettingByToken")
    public Map getMemberRuleSettingByToken(@RequestParam(TOKEN) String token);
    /**
     * create by: ws
     * description: TODO
     * 获取所有优惠券
     * create time: 16:45 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("getAllCoupon")
    List<Coupon> getAllCoupon();

    /**
     * create by: ws
     * description: TODO
     * 领取优惠券
     * create time: 16:47 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("/addCoupon")
    Integer addCoupon(@RequestBody Coupon coupon);
    /**
     * create by: ws
     * description: TODO
     * pa判断token是否为空
     * create time: 23:50 2020/3/13
     * * @Param: null
     * @return
     */
    @PostMapping("/tokenCheck")
    Boolean tokenCheck();
    /**
     * create by: ws
     * description: TODO
     * 根据openId查询该用户拥有的优惠券
     * create time: 14:21 2020/3/14
     * * @Param: null
     * @return
     */
    @PostMapping("/selectCouponByOpenId")
    public List<Coupon> selectCouponByOpenId();

    @PostMapping("/Test")
    public void test();

    /**
     *  根据用户id查询用户评论
     * @author cat
     * @date 2020/3/15 14:15
     * @param memberid:
    * @return com.qy105.aaa.base.ResultData
     */
    @PostMapping("/qureyPmsCommentByMemberID")
    public ResultData qureyPmsCommentByMemberID(@RequestParam("memberid") Long memberid);

    /**
     *  根据id删除评论（逻辑删除）
     * @author cat
     * @date 2020/3/15 14:50
     * @param id:
    * @return java.lang.Boolean
     */
    @GetMapping("/deletePmsCommentById")
    public Boolean deletePmsCommentById(@RequestParam("id") Long id);

    /**
     *  用户添加评论
     * @author cat
     * @date 2020/3/20 16:32
     * @param file:
    	 * @param id:
    	 * @param star:
    	 * @param content:
    * @return java.lang.Boolean
     */
    @PostMapping(value = "/addPmsComment",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addPmsComment(@RequestBody MultipartFile file, @RequestParam("id") Long id
            , @RequestParam("star") Integer star,@RequestParam("content") String content);

    /**
     *  ftp上传文件
     * @author cat
     * @date 2020/3/17 13:41
     * @param file:
    	 * @param token:
    * @return java.lang.Boolean
     */
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file, @RequestParam(TOKEN) String token);
    
    /**
     * create by: ws
     * description: TODO
     * create time: 0:47 2020/3/22
     * * @Param: null
     * @return 
     */
    @PostMapping("createOrder")
    Boolean createOrder(@RequestBody OmsCartItem omsCartItem,@RequestParam("addressId") Object addressId,@RequestParam("time") String time,@RequestParam("couponId") int couponId);
    /**
     * 添加地址
     * @param address
     * @return
     */
    @PostMapping("/addAddress")
    ResultData addAddress(@RequestBody Address address);
    @PostMapping("/updateAddress")
    ResultData updateAddress(@RequestBody Address address);
    @PostMapping("/deleteAddress")
    ResultData deleteAddress(@RequestBody Address address);
    @PostMapping("/selcetAddress")
    ResultData selcetAddress(@RequestBody Address address);
    @PostMapping("/deleteAllAddress")
    ResultData deleteAllAddress(@RequestBody Integer[] id);
    @PostMapping("/updateAddressStatus")
    ResultData updateAddresStatus(@RequestBody Address address);

    /**
     * 查询个人信息
     * @param member
     * @return
     */
    @PostMapping("/selectMember")
    ResultData selcetMember(@RequestBody Member member);

    /**
     * 修改个人信息
     * @param member
     * @return
     */
    @PostMapping("/updateMember")
    ResultData updateMember(@RequestBody Member member);

    @PostMapping("/useCoupon")
    int useCoupon(@RequestParam("couponId") Object couponId);
    /**
     * create by: pyr
     * description:逻辑删除订单
     * create time: 0:47 2020/3/22
     * * @Param: null
     * @return
     */
    @RequestMapping("/deleteOrder")
    Boolean deleteOrder(@RequestParam(ID) Long id);
}
