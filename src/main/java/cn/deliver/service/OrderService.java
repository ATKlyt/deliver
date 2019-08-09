package cn.deliver.service;

import cn.deliver.domain.Order;
import cn.deliver.domain.Result;

import java.util.List;

public interface OrderService {

    Result addOrderSelective(Order order);

    Result findWaitOrder(Integer driverUid);

    Result validateVillage(Integer orderId);

    Result driverConfirmOrder(Integer orderId,Integer suretyId);
}
