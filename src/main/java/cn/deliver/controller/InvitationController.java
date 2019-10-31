package cn.deliver.controller;

import cn.deliver.domain.*;
import cn.deliver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("invitation")
public class InvitationController {

    @Autowired
    InvitationService invitationService;
    @Autowired
    UserService userService;
    @Autowired
    AreaService areaService;
    @Autowired
    UserOrderService userOrderService;


    /**
     * 邀请司机
     * @param invitation
     * @return
     */
    @ResponseBody
    @RequestMapping("invite")
    public Result inviteDriver(@RequestBody Invitation invitation){
        invitation.setStatus("0");
        return invitationService.inviteDriver(invitation);
    }

    /**
     * 司机查看自己所有的邀请
     * @param parameters
     * @return
     */
    @ResponseBody
    @RequestMapping("driversInvitation")
    public Result findDriversInvitation(@RequestBody Map<String, Object> parameters){
        Integer driverUid = (Integer) parameters.get("driverUid");
        return invitationService.findDriversInvitation(driverUid);
    }


    /**
     * 用户查看自己所有的邀请
     * @param parameters
     * @return
     */
    @ResponseBody
    @RequestMapping("userOrdersInvitation")
    public Result findInvitationByUserOrderId(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        return invitationService.findInvitationByUserOrderId(userOrderId);
    }

    /**
     * 司机查找邀请详细信息
     * @param parameters
     * @return
     */
    @ResponseBody
    @RequestMapping("invitationDetail")
    public Result findInvitationDetail(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        Integer driverRouteId = (Integer) parameters.get("driverRouteId");
        Map<String, Object> map = new HashMap<>(16);
        //司机行程具体信息
        DriverRouteDetail driverRouteDetail = new DriverRouteDetail();
        DriverRoute driverRoute = invitationService.findInvitationRoute(userOrderId, driverRouteId);
        driverRouteDetail.setDriverRoute(driverRoute);
        //司机行程出发地
        driverRouteDetail.setOriginalArea
                (areaService.selectByPrimaryKey(driverRoute.getOriginalAreaId()));
        //司机行程目的地
        driverRouteDetail.setDestinationArea
                (areaService.selectByPrimaryKey(driverRoute.getDestinationAreaId()));
        //用户订单具体信息
        UserOrder userOrder = userOrderService.selectByPrimaryKey(userOrderId);
        Area consigneeArea = areaService.selectByPrimaryKey(userOrder.getConsigneeAreaId());
        Area deliverArea = areaService.selectByPrimaryKey(userOrder.getDeliverAreaId());
        //担保人信息
        UserRelated suretyRelate = userService.findNameAndPhoneByUid(userOrder.getUid());
        //收货人信息
        UserRelated contactRelate = userService.findNameAndPhoneByUid(consigneeArea.getCid());
        map.put("suretyRelate", suretyRelate);
        map.put("contactRelate", contactRelate);
        map.put("deliverArea", deliverArea);
        map.put("consigneeArea", consigneeArea);
        map.put("deliveryStartTime", userOrder.getDeliveryStartTime());
        map.put("deliveryEndTime", userOrder.getDeliveryEndTime());
        map.put("userOrderNumber", userOrder.getUserOrderNumber());
        map.put("driverRouteDetail", driverRouteDetail);
        return new Result("查询成功", "0", map);
    }

}
