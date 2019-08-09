package cn.deliver.service;

import cn.deliver.domain.Result;
import cn.deliver.domain.UserOrder;

public interface UserOrderService {
    Result addUserOrder(UserOrder userOrder);

    Result findNearByVillage(String village);

    Result findDetailByUserOrderId(Integer userOrderId);
}
