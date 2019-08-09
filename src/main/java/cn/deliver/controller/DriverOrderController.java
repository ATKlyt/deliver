package cn.deliver.controller;

import cn.deliver.domain.*;
import cn.deliver.service.DriverOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("driverOrder")
public class DriverOrderController {
    @Autowired
    DriverOrderService driverOrderService;


    /**
     *
     * @param parameters
     * @return
     */
    @RequestMapping("addDriverOrder")
    @ResponseBody
    public Result addDriverOrder(@RequestBody Map<String, Object> parameters){

        //之后与前端商量优化
        Area originalArea = new Area();
        Area consigneeArea = new Area();
        DriverOrder driverOrder = new DriverOrder();

        driverOrder.setUid((Integer) parameters.get("uid"));
        driverOrder.setCarry((Integer) parameters.get("carry"));
        driverOrder.setGoOff(new Timestamp((Long) parameters.get("goOff")));
        driverOrder.setDeadline(new Timestamp((Long) parameters.get("deadline")));

        originalArea.setUid(driverOrder.getUid());
        originalArea.setProvince((String) parameters.get("originalProvince"));
        originalArea.setCity((String) parameters.get("originalCity"));
        originalArea.setDistrict((String) parameters.get("originDistrict"));
        originalArea.setTown((String) parameters.get("originalTown"));
        originalArea.setVillage((String) parameters.get("originalVillage"));
        originalArea.setDetail((String) parameters.get("originDetail"));
        originalArea.setStatus("6");

        consigneeArea.setUid(driverOrder.getUid());
        consigneeArea.setProvince((String) parameters.get("consigneeProvince"));
        consigneeArea.setCity((String) parameters.get("consigneeCity"));
        consigneeArea.setDistrict((String) parameters.get("consigneeDistrict"));
        consigneeArea.setTown((String) parameters.get("consigneeTown"));
        consigneeArea.setVillage((String) parameters.get("consigneeVillage"));
        consigneeArea.setDetail((String) parameters.get("consigneeDetail"));
        consigneeArea.setStatus("7");



        //验证是否有资格
        return driverOrderService.addDriverOrder(originalArea, consigneeArea, driverOrder);
        //return null;
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
