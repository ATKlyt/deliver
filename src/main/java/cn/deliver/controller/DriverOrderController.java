package cn.deliver.controller;

import cn.deliver.domain.DriverOrderDetail;
import cn.deliver.domain.Result;
import cn.deliver.service.DriverOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("driverOrder")
public class DriverOrderController {

    @Autowired
    DriverOrderService driverOrderService;

    /**
     * @param driverOrderDetail
     * @return
     */
    @RequestMapping("addDriverOrder")
    @ResponseBody
    public Result addDriverOrder(@RequestBody DriverOrderDetail driverOrderDetail) throws InterruptedException {
        //验证是否有资格
        return driverOrderService.addDriverOrder(driverOrderDetail.getOriginalArea(),
                                                 driverOrderDetail.getConsigneeArea(),
                                                 driverOrderDetail.getDriverOrder());
    }


    /**
     * 根据司机行程id查询司机行程详细信息
     * authId/所属区域/目的地/汽车图片/星级评价与评论
     * @param parameters
     * @return
     */
    @RequestMapping("findDetail")
    @ResponseBody
    public Result findDetailByUserOrder(@RequestBody Map<String, Object> parameters){
        Integer driverOrderId = (Integer) parameters.get("driverOrderId");
        return driverOrderService.findDetailByDriverOrder(driverOrderId);
    }

    /**
     * 查询司机订单出发地址与用户订单发货地址相同的司机行程(村子相同)
     * 返回：司机行程id/姓名/车牌/出发地/出发时间
     * @param parameters  用户订单
     * @return
     */
    @RequestMapping("findNear")
    @ResponseBody
    public Result findNearByUserOrder(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        return driverOrderService.findNearByUserOrder(userOrderId);
    }



}
