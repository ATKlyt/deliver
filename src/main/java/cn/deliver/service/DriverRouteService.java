package cn.deliver.service;

import cn.deliver.domain.Area;
import cn.deliver.domain.DriverRoute;
import cn.deliver.domain.Result;

public interface DriverRouteService {
    /**
     * 发布行程
     * @param driverRoute 司机行程
     * @param originalArea 出发地
     * @param destinationArea 目的地
     * @return
     */
    Result addDriverRoute(DriverRoute driverRoute, Area originalArea, Area destinationArea);


    /**
     * 取消司机行程
     * @param driverRoute
     * @return
     */
    Result cancelDriverRoute(Integer userId, DriverRoute driverRoute);

    /**
     * 删除司机行程
     * @param driverRoute
     * @return
     */
    Result deleteDriverRoute(Integer userId, DriverRoute driverRoute);

    /**
     * 查找附近司机行程(司机姓名，电话，出发时间，出发地址)
     * @param userOrderId
     * @return
     */
    Result findNearByUserOrderId(Integer userOrderId, Integer pageNumber);

    /**
     * 查找司机行程相关具体信息(包括车牌号，车辆照片，行程目的地以及司机所属地)
     * @param driverRouteId
     * @return
     */
    Result findDetailByDriverRouteId(Integer driverRouteId);
}

