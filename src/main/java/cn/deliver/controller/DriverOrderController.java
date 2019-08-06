package cn.deliver.controller;

import cn.deliver.domain.*;
import cn.deliver.service.DriverOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("driverOrder")
public class DriverOrderController {
    @Autowired
    DriverOrderService driverOrderService;


    /**
     * 司机发布行程
     * @param areas
     * @param driverOrder
     * @return
     */
    @RequestMapping("addDriverOrder")
    @ResponseBody
    public Result addDriverOrder(AreaList areas, DriverOrder driverOrder){
        int status = driverOrderService.addDriverOrder(areas, driverOrder);
        return new Result("发布行程成功", "200");
    }






    /**
     * 撤销行程
     * @return
     */
    public Result cancelDriverOrder(){
        return null;
    }


    /**
     * 查找出发地与用户发货地(即所属地区)相同的司机订单
     * @param uid
     * @return
     */
    @RequestMapping("findNearby")
    @ResponseBody
    public Result findNearbyDriverOrder(Integer uid){
        List<DriverOrderMessage> data = driverOrderService.findNearbyDriverOrder(uid);
        if (data != null){
            return new Result("查询成功", "200", data);
        }else{
            return new Result("查询失败", "400");
        }
    }

}
