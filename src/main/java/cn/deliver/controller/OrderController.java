package cn.deliver.controller;


import cn.deliver.domain.Order;
import cn.deliver.domain.Result;
import cn.deliver.service.OrderService;
import cn.deliver.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 用户邀请司机
     * 可以邀请多个，多个司机
     * driverUid，userOrderId
     * @param order
     * @return
     */
    @RequestMapping("inviteDriver")
    @ResponseBody
    public Result inviteDriver(@RequestBody Order order){
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1,1);
        //封装数据
        //将订单状态设置为等待司机接受
        order.setStatus("1");
        //生成订单号
        order.setNo(String.valueOf(snowflakeIdWorker.nextId()));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        order.setCreateTime(timestamp);
        order.setUpdateTime(timestamp);
        return orderService.addOrderSelective(order);
    }

    /**
     * 获取该司机所有等待状态的order（等待司机接受,如果被其他司机接受了，可以显示）且不过期
     * @param parameters
     * @return
     */
    @RequestMapping("findWaitOrder")
    @ResponseBody
    public Result findWaitOrder(@RequestBody Map<String, Object> parameters){
        Integer driverUid = (Integer) parameters.get("driverUid");
        return orderService.findWaitOrder(driverUid);
    }



    /**
     * 判断是否需要担保人
     * 用户订单id和司机uid
     * @param parameters
     * @return
     */
    @RequestMapping("validateNeedSafety")
    @ResponseBody
    public Result validateNeedSafety(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        Integer driverUid = (Integer) parameters.get("driverUid");
        return orderService.validateNeedSafety(userOrderId, driverUid);
    }


    /**
     * 判断担保人符合要求
     * 用户订单id和司机uid
     * @param parameters
     * @return
     */
    @RequestMapping("validateSafety")
    @ResponseBody
    public Result validateSafety(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        String suretyAuthId = (String) parameters.get("suretyAuthId");
        return orderService.validateSafety(suretyAuthId, userOrderId);
    }


    /**
     * 司机主动接单
     * 用户订单id和司机uid必需，当该交易(即order)需要担保人时，则order的属性safetyId不为null
     * @param order
     * @return
     */
    @RequestMapping("receive")
    @ResponseBody
    public Result receiveUserOrder(@RequestBody Order order){
        return orderService.receiveUserOrder(order);
    }

    /**
     * 司机确认用户邀请
     * @param parameters
     * @return
     */
    @RequestMapping("driverConfirm")
    @ResponseBody
    public Result driverConfirmOrder(@RequestBody Map<String, Object> parameters){
        Integer orderId = (Integer) parameters.get("orderId");
        String suretyAuthId = (String) parameters.get("suretyAuthId");
        return orderService.driverConfirmOrder(orderId,suretyAuthId);
    }

}
