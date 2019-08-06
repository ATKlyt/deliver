package cn.deliver.service.impl;

import cn.deliver.dao.UserOrderDao;
import cn.deliver.domain.UserOrder;
import cn.deliver.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    UserOrderDao userOrderDao;

    @Override
    public int addUserOrder(UserOrder userOrder) {
        return userOrderDao.insertSelective(userOrder);
    }
}
