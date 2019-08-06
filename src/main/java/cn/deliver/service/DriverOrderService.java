package cn.deliver.service;

import cn.deliver.domain.AreaList;
import cn.deliver.domain.DriverOrder;
import cn.deliver.domain.DriverOrderMessage;

import java.util.List;

public interface DriverOrderService {

    List<DriverOrderMessage> findNearbyDriverOrder(Integer uid);


    int addDriverOrder(AreaList areas, DriverOrder driverOrder);
}
