package cn.deliver.service;

import cn.deliver.domain.Order;
import cn.deliver.domain.Result;

public interface OrderService {

    Result addOrderSelective(Order order);

    Result findWaitOrder(Integer driverUid);

    Result validateNeedSafety(Integer userOrderId, Integer uid);

    Result driverConfirmOrder(Integer orderId,Integer suretyId);

    Result receiveUserOrder(Order order);

    Result validateSafety(String suretyAuthId, Integer userOrderId);
}
