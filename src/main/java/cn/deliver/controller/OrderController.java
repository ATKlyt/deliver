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
     * driverUid，userOrderId，(suretyId)
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
     * @param parameters
     * @return
     */
    @RequestMapping("validateVillage")
    @ResponseBody
    public Result validateVillage(@RequestBody Map<String, Object> parameters){
        Integer orderId = (Integer) parameters.get("orderId");
        return orderService.validateVillage(orderId);
    }

    /**
     * 司机确认
     * @param parameters
     * @return
     */
    @RequestMapping("driverConfirm")
    @ResponseBody
    public Result driverConfirmOrder(@RequestBody Map<String, Object> parameters){
        Integer orderId = (Integer) parameters.get("orderId");
        Integer suretyId = (Integer) parameters.get("suretyId");
        return orderService.driverConfirmOrder(orderId,suretyId);
    }

}
