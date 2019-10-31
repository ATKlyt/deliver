package cn.deliver.service;

import cn.deliver.domain.Result;
import cn.deliver.domain.UserOrder;

public interface UserOrderService {
    UserOrder selectByPrimaryKey(Integer userOrderId);

    Result orderRelease(UserOrder userOrder);

    Result validateSurety(Integer suretyId, Integer shipperId);

    Result orderReceive(UserOrder userOrder);

    Result suretyRefuse(Integer suretyId, Integer userOrderId);

    Result suretyConfirm(Integer suretyId, Integer userOrderId);

    Result findAllByUid(Integer userId);

    Result findNeedSurety(Integer suretyId);

    Result findWaitDeliver(Integer cid);

    Result contactConfirm(Integer cid, Integer userOrderId);

    Result findNearByArea(String city, String district, String town, String village);

    Result driverAccept(UserOrder userOrder);
}
