package cn.deliver.controller;


import cn.deliver.domain.Order;
import cn.deliver.domain.Result;
import cn.deliver.service.OrderService;
import cn.deliver.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;





    /**
     * 用户邀请司机
     * 可以邀请多个，多个竞争
     * @param order
     * @return
     */
    @RequestMapping("inviteDriver")
    @ResponseBody
    public Result inviteDriver(Order order){
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1,1);
        //开始建立交易关系
        order.setStatus("1");
        order.setNo(String.valueOf(snowflakeIdWorker.nextId()));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        order.setCreateTime(timestamp);
        order.setUpdateTime(timestamp);
        int status = orderService.addOrderSelective(order);
        if (status > 0){
            return new Result("邀请成功，等待司机回应", "0");
        }else{
            return new Result("邀请失败", "1");
        }
    }

    /**
     * 获取该司机所有等待状态的order（等待司机接受,如果被其他司机接受了，可以显示）且不过期
     * @param driverUid
     * @return
     */
    public Result findWaitOrder(Integer driverUid){
        List<Order> orders = orderService.findWaitOrder(driverUid);
        return new Result("查询成功", "0", orders);
    }


    public Result driverConfirm(Integer orderId){
        //司机确认订单
        int status = orderService.updateDriverConfirm(orderId);
        //判断是否同村


        //
        //
        if (status > 0 ){
            return new Result("确认成功", "0");
        }else{
            return new Result("确认失败", "1");
        }

    }

}
