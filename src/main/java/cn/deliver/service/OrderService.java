package cn.deliver.service;

import cn.deliver.domain.Order;

import java.util.List;

public interface OrderService {

    int addOrderSelective(Order order);

    List<Order> findWaitOrder(Integer driverUid);

    int updateDriverConfirm(Integer orderId);
}
