package cn.deliver.service.impl;

import cn.deliver.dao.DriverInfoDao;
import cn.deliver.dao.OrderDao;
import cn.deliver.dao.UserOrderDao;
import cn.deliver.domain.Order;
import cn.deliver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserOrderDao userOrderDao;
    @Autowired
    DriverInfoDao driverInfoDao;


    @Override
    public int addOrderSelective(Order order) {
        return orderDao.insertSelective(order);
    }

    @Override
    public List<Order> findWaitOrder(Integer driverUid) {
        return orderDao.findWaitOrder(driverUid);
    }

    @Override
    public int updateDriverConfirm(Integer orderId) {
        return orderDao.updateDriverConfirm(orderId);
    }
}
