package cn.deliver.service;

import cn.deliver.domain.*;

import java.util.List;

public interface DriverOrderService {

    /**
     * 查询司机订单出发地址与用户订单发货地址相同的司机行程(村子相同)---->列表
     * @param uid
     * @return
     */
    Result findNearByUserOrder(Integer uid);




    /**
     * 根据司机行程id查询司机行程详细信息
     * @param driverOrderId
     * @return
     */
    Result findDetailByDriverOrder(Integer driverOrderId);

    Result addDriverOrder(Area originalArea, Area consigneeArea, DriverOrder driverOrder);
}
