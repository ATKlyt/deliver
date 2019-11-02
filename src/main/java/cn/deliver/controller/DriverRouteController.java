package cn.deliver.controller;

import cn.deliver.domain.Area;
import cn.deliver.domain.DriverRoute;
import cn.deliver.domain.DriverRouteDetail;
import cn.deliver.domain.Result;
import cn.deliver.service.DriverRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Map;

@RequestMapping("driverRoute")
@Controller
public class DriverRouteController {

    @Autowired
    DriverRouteService driverRouteService;

    /**
     * 司机发布行程
     * @param driverRouteDetail
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Result addDriverRoute(@RequestBody DriverRouteDetail driverRouteDetail){
        //将地址类型设置为出发地类型
        Area originalArea = driverRouteDetail.getOriginalArea();
        originalArea.setType("6");
        //将地址类型设置为目的地类型
        Area destinationArea = driverRouteDetail.getDestinationArea();
        destinationArea.setType("7");
        //设置该司机行程未过期
        DriverRoute driverRoute = driverRouteDetail.getDriverRoute();
        driverRoute.setStatus("0");
        //设置创建时间和更新时间
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        driverRoute.setCreateTime(nowTime);
        driverRoute.setUpdateTime(nowTime);
        return driverRouteService.addDriverRoute(driverRoute,originalArea,destinationArea);
    }

    /**
     * 取消行程
     * @param parameters
     * @return
     */
    @RequestMapping("cancel")
    @ResponseBody
    public Result cancelDriverRoute(@RequestBody Map<String, Object> parameters){
        Integer driverRouteId = (Integer) parameters.get("driverRouteId");
        Integer userId = (Integer) parameters.get("userId");
        DriverRoute driverRoute = new DriverRoute();
        driverRoute.setId(driverRouteId);
        driverRoute.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //将该司机行程设置为取消状态
        driverRoute.setStatus("1");
        return driverRouteService.cancelDriverRoute(userId, driverRoute);
    }

    /**
     * 删除司机行程
     * @param parameters
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Result deleteDriverRoute(@RequestBody Map<String, Object> parameters){
        Integer driverRouteId = (Integer) parameters.get("driverRouteId");
        Integer userId = (Integer) parameters.get("userId");
        DriverRoute driverRoute = new DriverRoute();
        driverRoute.setId(driverRouteId);
        driverRoute.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //将该司机行程设置为删除状态
        driverRoute.setStatus("3");
        return driverRouteService.deleteDriverRoute(userId, driverRoute);
    }

    /**
     * 根据司机行程id查询司机行程详细信息
     * 所属区域/目的地/汽车图片/车牌号/星级评价与评论
     * @param parameters
     * @return
     */
    @RequestMapping("findDetail")
    @ResponseBody
    public Result findDetailByDriverRouteId(@RequestBody Map<String, Object> parameters){
        Integer driverRouteId = (Integer) parameters.get("driverRouteId");
        return driverRouteService.findDetailByDriverRouteId(driverRouteId);
    }

    /**
     * 通过用户订单查找用户订单发货地附近司机行程
     * @param parameters  用户订单id
     * @return
     */
    @RequestMapping("findNear")
    @ResponseBody
    public Result findNearByUserOrderId(@RequestBody Map<String, Object> parameters){
        Integer userOrderId = (Integer) parameters.get("userOrderId");
        Integer pageNumber = (Integer) parameters.get("pageNumber");
        if (pageNumber == null){
            pageNumber = 1;
        }
        return driverRouteService.findNearByUserOrderId(userOrderId, pageNumber);
    }

}
